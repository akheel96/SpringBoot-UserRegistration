package com.registration.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registration.entity.model.User;

public interface UserRepository extends JpaRepository<User, String> {

	public User findByUserName(String userName);
}
