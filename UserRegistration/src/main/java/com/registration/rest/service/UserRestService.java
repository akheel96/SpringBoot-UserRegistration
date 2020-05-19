package com.registration.rest.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.registration.rest.model.UserDTO;

public interface UserRestService extends UserDetailsService {

	public List<UserDTO> getUsers(int page, int limit);

	public UserDTO addUser(UserDTO userDTO);

	public UserDTO getUserByUserName(String userName);
	
	public UserDTO getUserByEmail(String email);

	public UserDTO updateUser(String userName, UserDTO user);

	public void deleteUser(UserDTO user);
}
