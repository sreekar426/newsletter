package com.sreekar.dao;

import java.util.List;

import com.sreekar.entity.NewLetter;
import com.sreekar.entity.User;

public interface SubscriptionDao {
	
	public List<NewLetter> getAllNewsLetter();
	
	public void newUser(String email);
	
	public void insertSub(List<String> sub,String email);
	
	public User findByName(String name);
	
	public List<NewLetter> findListByName(List<String> names);
	
	public void updateSub(List<String> sub,String email);

}
