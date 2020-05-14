package com.registration.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registration.entity.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	public UserEntity findByUserName(String userName);
}
