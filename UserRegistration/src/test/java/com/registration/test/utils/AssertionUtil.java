package com.registration.test.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import com.registration.entity.model.AddressEntity;
import com.registration.entity.model.UserEntity;
import com.registration.rest.model.AddressDTO;
import com.registration.rest.model.UserDTO;
import com.registration.rest.model.response.AddressDetailsModel;
import com.registration.rest.model.response.UserResponseModel;

public class AssertionUtil {
	public static void assertUserDetails(UserEntity userEntity, UserDTO userDTO) {

		assertThat(userEntity.getEmail(), is(userDTO.getEmail()));
		assertThat(userEntity.getFirstName(), is(userDTO.getFirstName()));
		assertThat(userEntity.getLastName(), is(userDTO.getLastName()));
		assertThat(userEntity.getMobileno(), is(userDTO.getMobileno()));
		assertThat(userEntity.getPassword(), is(userDTO.getPassword()));

		List<AddressDTO> addressesDTO = userDTO.getAddress();
		assertNotNull(addressesDTO);

		userEntity.getAddress().forEach(a -> assertAddress(a,
				addressesDTO.stream().filter(ad -> a.getId().equals(ad.getId())).findAny().orElse(null)));
	}

	public static void assertUserDetails(UserDTO userDTO1, UserDTO userDTO2) {

		assertThat(userDTO1.getEmail(), is(userDTO2.getEmail()));
		assertThat(userDTO1.getFirstName(), is(userDTO2.getFirstName()));
		assertThat(userDTO1.getLastName(), is(userDTO2.getLastName()));
		assertThat(userDTO1.getMobileno(), is(userDTO2.getMobileno()));
		assertThat(userDTO1.getPassword(), is(userDTO2.getPassword()));

		List<AddressDTO> addressesDTO = userDTO2.getAddress();
		assertNotNull(addressesDTO);

		userDTO1.getAddress().forEach(a -> assertAddress(a,
				addressesDTO.stream().filter(ad -> a.getId().equals(ad.getId())).findAny().orElse(null)));
	}

	public static void assertUserDetails(UserResponseModel userResponseModel, UserDTO userDTO) {

		assertThat(userResponseModel.getEmail(), is(userDTO.getEmail()));
		assertThat(userResponseModel.getFirstName(), is(userDTO.getFirstName()));
		assertThat(userResponseModel.getLastName(), is(userDTO.getLastName()));
		assertThat(userResponseModel.getMobileno(), is(userDTO.getMobileno()));

		List<AddressDTO> addressesDTO = userDTO.getAddress();
		assertNotNull(addressesDTO);

		userResponseModel.getAddress().forEach(a -> assertAddress(a,
				addressesDTO.stream().filter(ad -> a.getId().equals(ad.getId())).findAny().orElse(null)));
	}

	public static void assertAddress(AddressEntity address1, AddressDTO address2) {

		assertNotNull(address2);
		assertThat(address1.getCity(), is(address2.getCity()));
		assertThat(address1.getCountry(), is(address2.getCountry()));
		assertThat(address1.getId(), is(address2.getId()));
		assertThat(address1.getPincode(), is(address2.getPincode()));
		assertThat(address1.getType(), is(address2.getType()));
		assertThat(address1.getStreetName(), is(address2.getStreetName()));

	}

	public static void assertAddress(AddressDetailsModel address1, AddressDTO address2) {

		assertNotNull(address2);
		assertThat(address1.getCity(), is(address2.getCity()));
		assertThat(address1.getCountry(), is(address2.getCountry()));
		assertThat(address1.getId(), is(address2.getId()));
		assertThat(address1.getPincode(), is(address2.getPincode()));
		assertThat(address1.getType(), is(address2.getType()));
		assertThat(address1.getStreetName(), is(address2.getStreetName()));

	}

	public static void assertAddress(AddressDTO addressEntity, AddressDTO addressDto) {

		assertNotNull(addressDto);
		assertThat(addressEntity.getCity(), is(addressDto.getCity()));
		assertThat(addressEntity.getCountry(), is(addressDto.getCountry()));
		assertThat(addressEntity.getId(), is(addressDto.getId()));
		assertThat(addressEntity.getPincode(), is(addressDto.getPincode()));
		assertThat(addressEntity.getType(), is(addressDto.getType()));
		assertThat(addressEntity.getStreetName(), is(addressDto.getStreetName()));

	}

}
