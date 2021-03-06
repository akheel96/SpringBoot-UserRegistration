package com.registration.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.registration.annotation.VerifyUser;
import com.registration.app.event.OnRegistrationCompleteEvent;
import com.registration.rest.manager.UserRestManager;
import com.registration.rest.model.request.UserSignUpRequestModel;
import com.registration.rest.model.response.UserResponseModel;

@RestController
public class UserRestController {

	@Autowired
	private UserRestManager userManager;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@PostMapping(value = "/users")
	public ResponseEntity<UserResponseModel> addUser(@Valid @RequestBody UserSignUpRequestModel user) {
		UserResponseModel createdUser = userManager.createUser(user);
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(createdUser));
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@VerifyUser
	@PutMapping(value = "/users/{uname}")
	public ResponseEntity<UserResponseModel> updateUser(@RequestBody UserSignUpRequestModel user,
			@PathVariable(value = "uname") String uname) {
		return new ResponseEntity<>(userManager.updateUser(uname, user), HttpStatus.OK);
	}

	@VerifyUser
	@GetMapping(value = "/users/{uname}")
	public ResponseEntity<UserResponseModel> getUser(@PathVariable(value = "uname") String uname) {
		return new ResponseEntity<>(userManager.getUser(uname), HttpStatus.OK);

	}

	@DeleteMapping(value = "/users/{uname}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value = "uname") String uname) {
		userManager.deleteUser(uname);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/users")
	public ResponseEntity<List<UserResponseModel>> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "20") int limit) {
		return new ResponseEntity<>(userManager.getUsers(page, limit), HttpStatus.OK);
	}
	
	@PutMapping(value = "/verify-user")
	public ResponseEntity<Void> verifyUser(@RequestParam(value = "token") String token) {
		userManager.verifyUser(token);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
