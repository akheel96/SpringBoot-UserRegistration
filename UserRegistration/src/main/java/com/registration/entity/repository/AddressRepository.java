package com.registration.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registration.entity.model.AddressEntity;
import com.registration.entity.model.UserEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String>{

	public List<AddressEntity> findByUser(UserEntity user);
	
	public AddressEntity findByIdAndUser(String id, UserEntity user);
	
}
