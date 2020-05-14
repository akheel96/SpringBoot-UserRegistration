package com.registration.rest.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.registration.constants.ErrorMessages;
import com.registration.exception.BadRequestException;
import com.registration.exception.NotFoundException;
import com.registration.rest.model.UserDTO;
import com.registration.rest.model.request.UserSignUpRequestDTO;
import com.registration.rest.model.response.UserResponseDTO;
import com.registration.rest.service.UserRestService;

@Component
public class UserManager {

	@Autowired
	UserRestService userService;

	public UserResponseDTO createUser(UserSignUpRequestDTO userRequest) {
		UserDTO user = toUserDTO(userRequest);

		if (userService.getUserByUserName(user.getUserName()) != null) {
			throw new BadRequestException(ErrorMessages.USER_ALREADY_EXISTS.getErrorMessage());
		}
		UserDTO createdUser = userService.addUser(user);
		return toUserResponeDTO(createdUser);
	}

	public UserResponseDTO getUser(String userName) {

		UserDTO user = userService.getUserByUserName(userName);
		if(user == null) {
			throw new NotFoundException(ErrorMessages.USER_NOT_FOUND.getErrorMessage());
		}
		return toUserResponeDTO(user);
	}

	private UserDTO toUserDTO(UserSignUpRequestDTO user) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

	private UserResponseDTO toUserResponeDTO(UserDTO userDTO) {
		UserResponseDTO user = new UserResponseDTO();
		BeanUtils.copyProperties(userDTO, user);
		return user;
	}

}
