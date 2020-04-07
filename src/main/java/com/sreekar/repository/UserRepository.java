package com.sreekar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sreekar.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByEmail(String email);

}
