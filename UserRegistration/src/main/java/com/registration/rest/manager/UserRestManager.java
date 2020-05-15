package com.registration.rest.manager;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.registration.constants.ErrorMessages;
import com.registration.exception.BadRequestException;
import com.registration.exception.NotFoundException;
import com.registration.rest.model.UserDTO;
import com.registration.rest.model.request.UserSignUpRequestModel;
import com.registration.rest.model.response.UserResponseModel;
import com.registration.rest.service.UserRestService;
import com.registration.utils.EntityDtoMappingUtil;

@Component
public class UserRestManager {

	@Autowired
	UserRestService userService;

	public UserResponseModel createUser(UserSignUpRequestModel userRequest) {
		UserDTO userDTO = EntityDtoMappingUtil.toUserDTO(userRequest);

		if (userService.getUserByUserName(userDTO.getUserName()) != null) {
			throw new BadRequestException(ErrorMessages.USER_ALREADY_EXISTS.getErrorMessage());
		}
		UserDTO createdUser = userService.addUser(userDTO);
		return EntityDtoMappingUtil.toUserResponeModel(createdUser);
	}

	public UserResponseModel getUser(String userName) {

		UserDTO userDTO = userService.getUserByUserName(userName);
		if (userDTO == null) {
			throw new NotFoundException(ErrorMessages.USER_NOT_FOUND.getErrorMessage());
		}
		return EntityDtoMappingUtil.toUserResponeModel(userDTO);
	}

	public List<UserResponseModel> getUsers(int page, int limit) {
		List<UserDTO> users = userService.getUsers(page, limit);
		return users.stream().map(EntityDtoMappingUtil::toUserResponeModel).collect(Collectors.toList());
	}

	public UserResponseModel updateUser(String userName, UserSignUpRequestModel user) {

		UserDTO userDTO = userService.getUserByUserName(userName);
		if (userDTO == null) {
			throw new NotFoundException(ErrorMessages.USER_NOT_FOUND.getErrorMessage());
		}
		updateUserDTO(userDTO, user);
		return EntityDtoMappingUtil.toUserResponeModel(userService.updateUser(userName, userDTO));
	}

	public void deleteUser(String userName) {

		UserDTO userDTO = userService.getUserByUserName(userName);
		userService.deleteUser(userDTO);
	}

	private void updateUserDTO(UserDTO toUser, UserSignUpRequestModel fromUser) {
		String firstName = fromUser.getFirstName();
		if (firstName != null && !firstName.isEmpty()) {
			toUser.setFirstName(firstName);
		}
		String lastName = fromUser.getLastName();
		if (lastName != null && !lastName.isEmpty()) {
			toUser.setLastName(lastName);
		}
		
		String mobileno = fromUser.getMobileno();
		if (mobileno != null && !mobileno.isEmpty()) {
			toUser.setMobileno(mobileno);
		}
	}

}
