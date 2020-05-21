package com.registration.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registration.annotation.VerifyUser;
import com.registration.rest.manager.AddressRestManager;
import com.registration.rest.model.response.AddressDetailsModel;

@RestController
@RequestMapping(value = "users/{uname}/address")
public class AddressRestController {

	@Autowired
	private AddressRestManager addressManager;

	@VerifyUser
	@GetMapping
	public ResponseEntity<List<AddressDetailsModel>> getAddress(@PathVariable(value = "uname") String userName) {
		return new ResponseEntity<>(addressManager.getAddress(userName), HttpStatus.OK);
	}

	@VerifyUser
	@GetMapping(value = "/{id}")
	public ResponseEntity<AddressDetailsModel> getAddress(@PathVariable(value = "uname") String userName,
			@PathVariable(value = "id") String id) {
		return new ResponseEntity<>(addressManager.getAddress(userName, id), HttpStatus.OK);
	}
}
