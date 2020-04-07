package com.sreekar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sreekar.dao.SubscriptionDao;
import com.sreekar.entity.NewLetter;
import com.sreekar.entity.User;
import com.sreekar.model.NewsLetterDto;

@Service
public class SubscribeImpl implements ISubscrition {

	@Autowired
	private SubscriptionDao dao;

	@Override
	public List<NewsLetterDto> getAllNewLetters() {
		List<NewsLetterDto> newsLetterList = new ArrayList<>();
		List<NewLetter> list = dao.getAllNewsLetter();

		list.forEach(obj -> {

			NewsLetterDto letter = new NewsLetterDto();
			letter.setUrl(obj.getUrl());
			letter.setName(obj.getName());
			newsLetterList.add(letter);

		});

		return newsLetterList;
	}

	@Override
	public void insertUser(String email) {
		
		dao.newUser(email);

	}

	@Override
	public void insertSubscription(List<String> sub , String email) {
		dao.insertSub(sub,email);
	}

	@Override
	public User findUserwithId(String name) {
		User user=dao.findByName(name);
		return user;
	}

	@Override
	public List<NewsLetterDto> findListOfSub(List<String> names) {
		List<NewsLetterDto> newsLetterList = new ArrayList<>();
		List<NewLetter> list=dao.findListByName(names);
		list.forEach(obj -> {

			NewsLetterDto letter = new NewsLetterDto();
			letter.setUrl(obj.getUrl());
			letter.setName(obj.getName());
			newsLetterList.add(letter);

		});

		return newsLetterList;
	}

	@Override
	public void upateSubscription(List<String> sub, String email) {
		dao.updateSub(sub, email);
		
	}

}
