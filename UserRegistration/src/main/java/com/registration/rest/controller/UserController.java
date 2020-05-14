package com.registration.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.registration.rest.manager.UserManager;
import com.registration.rest.model.request.UserSignUpRequestDTO;
import com.registration.rest.model.response.UserSignUpResponseDTO;

@RestController
public class UserController {

	@Autowired
	private UserManager userManager;

	@PostMapping(value = "/users")
	public ResponseEntity<UserSignUpResponseDTO> addUser(@RequestBody UserSignUpRequestDTO user) {
		return new ResponseEntity<>(userManager.createUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/users")
	public String updateUser() {
		return "Update user Called";
	}

}
