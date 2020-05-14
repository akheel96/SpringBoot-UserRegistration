package com.registration.rest.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.registration.rest.model.UserDTO;
import com.registration.rest.model.request.UserSignUpRequestDTO;
import com.registration.rest.model.response.UserSignUpResponseDTO;
import com.registration.rest.service.UserRestService;

@Component
public class UserManager {

	@Autowired
	UserRestService userService;
	
	public UserSignUpResponseDTO createUser(UserSignUpRequestDTO userRequest) {
		UserDTO user = toUserDTO(userRequest);
		
		if(userService.getUserByUserName(user.getUserName()) != null) {
			throw new RuntimeException("User already exists");
		}
		UserDTO createdUser = userService.addUser(user);
		return toUserResponeDTO(createdUser);
	}
	
	private UserDTO toUserDTO(UserSignUpRequestDTO user) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

	private UserSignUpResponseDTO toUserResponeDTO(UserDTO userDTO) {
		UserSignUpResponseDTO user = new UserSignUpResponseDTO();
		BeanUtils.copyProperties(userDTO, user);
		return user;
	}
	
}
