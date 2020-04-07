package com.sreekar.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sreekar.entity.NewLetter;
import com.sreekar.entity.Subscription;
import com.sreekar.entity.User;
import com.sreekar.repository.NewsletterRepository;
import com.sreekar.repository.SubscriptionRepository;
import com.sreekar.repository.UserRepository;

@Repository
@Transactional
public class SubscriptionDaoImpl implements SubscriptionDao{

	@Autowired
	private NewsletterRepository newsletterRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Override
	public List<NewLetter> getAllNewsLetter() {
		
		return newsletterRepository.findAll();
	}

	@Override
	public void newUser(String email) {
		
		User usr=new User();
		usr.setEmail(email);
		if(emailCheck(email)) {
		userRepository.save(usr);
		}
		
	}

	public void insertSub(List<String> sub, String email) {
		LinkedHashMap<String,Subscription> myMap=new LinkedHashMap();
		List<Subscription> subList = new ArrayList<Subscription>();
		User user = userRepository.findByEmail(email);
		
		List<Subscription> subResult = subscriptionRepository.findAllBySubscriptionNameInAndUserId(sub, user.getId());
		if (subResult.isEmpty()) {
			sub.forEach(obj -> {

				Subscription subscription = new Subscription();
				subscription.setSubscriptionName(obj);
				subscription.setSubscriptionStatus(true);
				subscription.setUserId(user.getId());
				subList.add(subscription);
			});
			subscriptionRepository.saveAll(subList);
		} else {

			sub.forEach(obj -> {

				Subscription subscription = new Subscription();
				subscription.setSubscriptionName(obj);
				subscription.setSubscriptionStatus(true);
				subscription.setUserId(user.getId());
				myMap.put(obj, subscription);
			});

			subResult.forEach(obj -> {
				if(obj.isSubscriptionStatus()) {
				myMap.remove(obj.getSubscriptionName());
				}else if(! obj.isSubscriptionStatus()){
					subscriptionRepository.deleteById(obj.getId());
				}

			});
			
			for (Entry<String, Subscription> entry : myMap.entrySet()) {
				subList.add(entry.getValue());
			}

		}

		subscriptionRepository.saveAll(subList);

	}

		

	

	@Override
	public User findByName(String name) {
		return userRepository.findByEmail(name);
	}

	@Override
	public List<NewLetter> findListByName(List<String> names) {
		
		return newsletterRepository.findAllByNameIn(names);
	}
	
	private boolean emailCheck(String email) {
		User user= findByName(email);
		if(user==null) {
			return true;
		}
		return false;
	
		
	}

	@Override
	public void updateSub(List<String> sub, String email) {
		List<Subscription> subList=new ArrayList<Subscription>();
		User user=userRepository.findByEmail(email);
		
		sub.forEach(obj->{
			
			Subscription subscription=new Subscription();
			subscription.setSubscriptionName(obj);
			subscription.setSubscriptionStatus(false);
			subscription.setUserId(user.getId());
			subList.add(subscription);
		});
		
		
	
		subscriptionRepository.saveAll(subList);
		
	}

}
