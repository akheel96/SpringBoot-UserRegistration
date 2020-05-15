package com.registration.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registration.entity.model.AddressEntity;
import com.registration.entity.model.UserEntity;
import com.registration.entity.repository.AddressRepository;
import com.registration.rest.model.AddressDTO;
import com.registration.rest.model.UserDTO;
import com.registration.rest.service.AddressRestService;
import com.registration.utils.EntityDtoMappingUtil;

@Service
public class AddressRestServiceImpl implements AddressRestService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<AddressDTO> getAddress(UserDTO user) {

		UserEntity userEntity = EntityDtoMappingUtil.toUserEntity(user);
		return EntityDtoMappingUtil.toAddressDTO(addressRepository.findByUser(userEntity));
	}

	@Override
	public AddressDTO getAddress(UserDTO user, String addressId) {
		UserEntity userEntity = EntityDtoMappingUtil.toUserEntity(user);
		AddressEntity address = addressRepository.findByIdAndUser(addressId, userEntity);
		return address != null ? EntityDtoMappingUtil.toAddressDTO(address) : null;
	}

}
