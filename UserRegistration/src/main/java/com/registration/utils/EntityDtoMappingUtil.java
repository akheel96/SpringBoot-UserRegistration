package com.registration.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.registration.entity.model.AddressEntity;
import com.registration.entity.model.UserEntity;
import com.registration.rest.model.AddressDTO;
import com.registration.rest.model.UserDTO;
import com.registration.rest.model.request.UserSignUpRequestModel;
import com.registration.rest.model.response.AddressDetailsModel;
import com.registration.rest.model.response.UserResponseModel;

public class EntityDtoMappingUtil {

	private static ModelMapper mapper = new ModelMapper();

	/*
	 * User Mappings
	 */
	public static UserDTO toUserDTO(UserEntity user) {
		UserDTO userDTO = new UserDTO();
		mapper.map(user, userDTO);
		return userDTO;
	}

	public static UserEntity toUserEntity(UserDTO userDTO) {
		UserEntity user = new UserEntity();
		mapper.map(userDTO, user);
		return user;
	}

	public static UserDTO toUserDTO(UserSignUpRequestModel user) {
		UserDTO userDTO = new UserDTO();
		mapper.map(user, userDTO);
		return userDTO;
	}

	public static UserSignUpRequestModel toUserSignUpRequestModel(UserDTO user) {
		UserSignUpRequestModel model = new UserSignUpRequestModel();
		mapper.map(user, model);
		return model;
	}

	public static UserResponseModel toUserResponeModel(UserDTO userDTO) {
		UserResponseModel user = new UserResponseModel();
		mapper.map(userDTO, user);
		return user;
	}

	/*
	 * Address mappings
	 */

	public static List<AddressDTO> toAddressDTO(List<AddressEntity> address) {
		return address.stream().map(EntityDtoMappingUtil::toAddressDTO).collect(Collectors.toList());
	}

	public static AddressDTO toAddressDTO(AddressEntity address) {
		AddressDTO addressDTO = new AddressDTO();
		mapper.map(address, addressDTO);
		return addressDTO;
	}

	public static List<AddressDetailsModel> toAddressDetailsModel(List<AddressDTO> address) {
		return address.stream().map(EntityDtoMappingUtil::toAddressDetailsModel).collect(Collectors.toList());
	}

	public static AddressDetailsModel toAddressDetailsModel(AddressDTO address) {
		AddressDetailsModel addressDetailsDTO = new AddressDetailsModel();
		mapper.map(address, addressDetailsDTO);
		return addressDetailsDTO;
	}
}
