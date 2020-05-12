package com.registration.rest.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.registration.rest.model.UserDTO;

public interface UserRestService extends UserDetailsService{

	public List<UserDTO> getUsers();

	public UserDTO addUser(UserDTO userDTO);

	public UserDTO getUserByUserName(String userName);
}
