package com.registration.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.registration.rest.manager.UserManager;
import com.registration.rest.model.request.UserRequestDTO;
import com.registration.rest.model.response.UserResponseDTO;

@RestController
public class UserController {

	@Autowired
	private UserManager userManager;

	@PostMapping(value = "/users")
	public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO user) {
		return new ResponseEntity<>(userManager.createUser(user), HttpStatus.CREATED);
	}

}
