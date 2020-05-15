package com.registration.rest.manager;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.registration.constants.ErrorMessages;
import com.registration.exception.BadRequestException;
import com.registration.exception.NotFoundException;
import com.registration.rest.model.UserDTO;
import com.registration.rest.model.request.UserSignUpRequestDTO;
import com.registration.rest.model.response.UserDetailsDTO;
import com.registration.rest.service.UserRestService;

@Component
public class UserManager {

	@Autowired
	UserRestService userService;

	public UserDetailsDTO createUser(UserSignUpRequestDTO userRequest) {
		UserDTO userDTO = toUserDTO(userRequest);

		if (userService.getUserByUserName(userDTO.getUserName()) != null) {
			throw new BadRequestException(ErrorMessages.USER_ALREADY_EXISTS.getErrorMessage());
		}

		UserDTO createdUser = userService.addUser(userDTO);
		return toUserDetailsDTO(createdUser);
	}

	public UserDetailsDTO getUser(String userName) {

		UserDTO userDTO = userService.getUserByUserName(userName);
		if (userDTO == null) {
			throw new NotFoundException(ErrorMessages.USER_NOT_FOUND.getErrorMessage());
		}
		return toUserDetailsDTO(userDTO);
	}

	public List<UserDetailsDTO> getUsers(int page, int limit) {
		List<UserDTO> users = userService.getUsers(page, limit);
		return users.stream().map(this::toUserDetailsDTO).collect(Collectors.toList());
	}

	public UserDetailsDTO updateUser(String userName, UserSignUpRequestDTO user) {

		UserDTO userDTO = userService.getUserByUserName(userName);
		if (userDTO == null) {
			throw new NotFoundException(ErrorMessages.USER_NOT_FOUND.getErrorMessage());
		}
		updateUserDTO(userDTO.getUserDetails(), user);
		return toUserDetailsDTO(userService.updateUser(userName, userDTO));
	}

	public void deleteUser(String userName) {

		UserDTO userDTO = userService.getUserByUserName(userName);
		userService.deleteUser(userDTO);
	}

	private void updateUserDTO(UserDetailsDTO toUser, UserSignUpRequestDTO fromUser) {
		String firstName = fromUser.getFirstName();
		if (firstName != null && !firstName.isEmpty()) {
			toUser.setFirstName(firstName);
		}
		String lastName = fromUser.getLastName();
		if (lastName != null && !lastName.isEmpty()) {
			toUser.setLastName(lastName);
		}
		String country = fromUser.getCountry();
		if (country != null && !country.isEmpty()) {
			toUser.setCountry(country);
		}
		String mobileno = fromUser.getMobileno();
		if (mobileno != null && !mobileno.isEmpty()) {
			toUser.setMobileno(mobileno);
		}
	}

	private UserDTO toUserDTO(UserSignUpRequestDTO user) {
		UserDTO userDTO = new UserDTO();

		UserDetailsDTO userDetails = new UserDetailsDTO();
		BeanUtils.copyProperties(user, userDetails);
		userDTO.setUserDetails(userDetails);
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

	private UserDetailsDTO toUserDetailsDTO(UserDTO userDTO) {
		UserDetailsDTO user = new UserDetailsDTO();
		BeanUtils.copyProperties(userDTO.getUserDetails(), user);
		return user;
	}

}
