package com.registration.rest.service;

import java.util.List;

import com.registration.rest.model.AddressDTO;
import com.registration.rest.model.UserDTO;

public interface AddressRestService {
	
	public List<AddressDTO> getAddress(UserDTO user);
	
	public AddressDTO getAddress(UserDTO user, String userId);
}
