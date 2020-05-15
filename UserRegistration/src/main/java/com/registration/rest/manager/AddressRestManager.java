package com.registration.rest.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.registration.constants.ErrorMessages;
import com.registration.exception.NotFoundException;
import com.registration.rest.model.AddressDTO;
import com.registration.rest.model.UserDTO;
import com.registration.rest.model.response.AddressDetailsModel;
import com.registration.rest.service.AddressRestService;
import com.registration.rest.service.UserRestService;
import com.registration.utils.EntityDtoMappingUtil;

@Component
public class AddressRestManager {

	@Autowired
	private AddressRestService addressService;

	@Autowired
	private UserRestService userService;

	public List<AddressDetailsModel> getAddress(String userName) {
		UserDTO user = userService.getUserByUserName(userName);
		if (user == null) {
			throw new NotFoundException(ErrorMessages.USER_NOT_FOUND.getErrorMessage());
		}
		return EntityDtoMappingUtil.toAddressDetailsModel(addressService.getAddress(user));
	}
	
	public AddressDetailsModel getAddress(String userName, String id) {
		UserDTO user = userService.getUserByUserName(userName);
		if (user == null) {
			throw new NotFoundException(ErrorMessages.USER_NOT_FOUND.getErrorMessage());
		}
		AddressDTO address = addressService.getAddress(user, id);
		if(address == null) {
			throw new NotFoundException(ErrorMessages.ADDRESS_NOT_FOUND.getErrorMessage());
		}
		return EntityDtoMappingUtil.toAddressDetailsModel(address);
	}

}
