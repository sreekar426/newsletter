package com.sreekar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sreekar.entity.NewLetter;

public interface NewsletterRepository extends JpaRepository<NewLetter, String> {

	List<NewLetter> findAll();
	
	List<NewLetter>  findAllByNameIn(List<String> test);
	
}
