package com.sreekar.service;

import java.util.List;

import com.sreekar.entity.User;
import com.sreekar.model.NewsLetterDto;

public interface ISubscrition {

	public List<NewsLetterDto> getAllNewLetters();
	
	public void insertUser(String email);
	
	public void insertSubscription(List<String> sub,String email);
	
	public User findUserwithId(String name);
	
	public List<NewsLetterDto> findListOfSub(List<String> string);
	
	public void upateSubscription(List<String> sub,String email);
	
	

}
