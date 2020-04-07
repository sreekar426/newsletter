package com.sreekar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sreekar.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, String>{

	
	List<Subscription> findAllBySubscriptionNameInAndUserId(List<String> ids,int id);
	
	void deleteById(int id);
	
}
