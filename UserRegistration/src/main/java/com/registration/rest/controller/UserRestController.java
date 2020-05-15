package com.registration.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.registration.rest.manager.UserManager;
import com.registration.rest.model.request.UserSignUpRequestDTO;
import com.registration.rest.model.response.UserDetailsDTO;

@RestController
@RequestMapping(value = { "/users" })
public class UserRestController {

	@Autowired
	private UserManager userManager;

	@PostMapping
	public ResponseEntity<UserDetailsDTO> addUser(@RequestBody UserSignUpRequestDTO user) {
		return new ResponseEntity<>(userManager.createUser(user), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{uname}")
	public ResponseEntity<UserDetailsDTO> updateUser(@RequestBody UserSignUpRequestDTO user,
			@PathVariable(value = "uname") String uname) {
		return new ResponseEntity<>(userManager.updateUser(uname, user), HttpStatus.OK);
	}

	@GetMapping(value = "/{uname}")
	public ResponseEntity<UserDetailsDTO> getUser(@PathVariable(value = "uname") String uname) {
		return new ResponseEntity<>(userManager.getUser(uname), HttpStatus.OK);

	}

	@DeleteMapping(value = "/{uname}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value = "uname") String uname) {
		userManager.deleteUser(uname);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public ResponseEntity<List<UserDetailsDTO>> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "20") int limit) {
		return new ResponseEntity<>(userManager.getUsers(page, limit), HttpStatus.OK);
	}

}
