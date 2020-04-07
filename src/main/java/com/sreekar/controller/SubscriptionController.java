package com.sreekar.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sreekar.entity.Subscription;
import com.sreekar.entity.User;
import com.sreekar.model.NewsLetterDto;
import com.sreekar.model.UtilityDto;
import com.sreekar.repository.SubscriptionRepository;
import com.sreekar.service.ISubscrition;

@Controller
public class SubscriptionController {

	@Autowired
	SubscriptionRepository subscriptionRepository;
	@Autowired
	private ISubscrition subscription;
	ArrayList<UtilityDto> myList = new ArrayList<>();

	@GetMapping("/getAll")
	@ResponseBody
	public List<NewsLetterDto> getAllSubscription() {

		return subscription.getAllNewLetters();

	}

	@PostMapping("/getSub")
	@ResponseBody
	public void getSubscribe(UtilityDto dto) {

		List<String> list = new ArrayList<>();

		subscription.insertUser(dto.getEmail());

		if (null != dto.getSelector()) {
			String[] name = dto.getSelector().split(",");

			for (String result : name) {
				list.add(result);
			}
		}

		subscription.insertSubscription(list, dto.getEmail());

	}

	@GetMapping("getByEmail/{email}")
	@ResponseBody
	public List<NewsLetterDto> getAllEmailSubscription(@PathVariable String email) {
		User use = subscription.findUserwithId(email);
		List<String> subsList = new ArrayList<>();
		use.getSubscriptions().forEach(obj -> {
			if (obj.isSubscriptionStatus())
				subsList.add(obj.getSubscriptionName());

		});
		
		return subscription.findListOfSub(subsList);

	}
	
	@PostMapping("/unSub")
	@ResponseBody
	public void getUnSubscribe(UtilityDto dto) {

		List<Subscription> list = new ArrayList<>();

		User user = subscription.findUserwithId(dto.getEmail());
		if (null != dto.getUnselector()) {
			String[] name = dto.getUnselector().split(",");

			for (String result : name) {

				Subscription unsub = new Subscription();

				Optional<Subscription> filteredMap = user.getSubscriptions().stream()
						.filter(obj -> obj.getSubscriptionName().equals(result)).findAny();
				if (filteredMap.isPresent()) {

					unsub.setSubscriptionName(result);
					unsub.setSubscriptionStatus(false);
					unsub.setUserId(user.getId());
					unsub.setId(filteredMap.get().getId());
					list.add(unsub);
				}

			}
		}
		subscriptionRepository.saveAll(list);
	}

}
