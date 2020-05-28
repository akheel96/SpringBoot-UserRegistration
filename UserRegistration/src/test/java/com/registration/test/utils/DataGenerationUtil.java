package com.registration.test.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.registration.entity.model.AddressEntity;
import com.registration.entity.model.UserEntity;
import com.registration.rest.model.AddressDTO;
import com.registration.rest.model.UserDTO;

public class DataGenerationUtil {

	public static List<UserEntity> createUserEntities(int noOfUsers, int noOfAddresses) {
		List<UserEntity> users = new ArrayList<>();
		for (int i = 0; i < noOfUsers; i++) {
			users.add(createUserEntity(noOfAddresses));
		}
		return users;
	}

	public static UserEntity createUserEntity(int noOfAddress) {

		UserEntity userEntity = new UserEntity();
		Faker faker = new Faker();
		Address address = faker.address();
		userEntity.setUserName(address.firstName());
		userEntity.setFirstName(address.firstName());
		userEntity.setLastName(address.lastName());
		userEntity.setMobileno(faker.phoneNumber().cellPhone());
		userEntity.setPassword(faker.bothify("??####???@"));
		userEntity.setEmail(faker.bothify("????##@gmail.com"));
		userEntity.setAddress(createAddressEntities(noOfAddress));
		return userEntity;

	}

	public static List<AddressEntity> createAddressEntities(int size) {
		List<AddressEntity> addresses = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			addresses.add(createAddressEntity());
		}
		return addresses;
	}

	public static AddressEntity createAddressEntity() {

		AddressEntity address = new AddressEntity();
		ArrayList<String> types = new ArrayList<>(Arrays.asList("billing", "personal"));

		Faker faker = new Faker();
		Address fakeAddress = faker.address();
		address.setCity(fakeAddress.city());
		address.setCountry(fakeAddress.country());
		address.setPincode(fakeAddress.countryCode());
		address.setStreetName(fakeAddress.streetName());
		address.setId(faker.bothify("???###?#?#??##"));
		address.setType(types.get(new Random().nextInt(types.size())));
		return address;

	}

	public static List<UserDTO> createUserDTOs(int noOfUsers, int noOfAddresses) {
		List<UserDTO> users = new ArrayList<>();
		for (int i = 0; i < noOfUsers; i++) {
			users.add(createUserDTO(noOfAddresses));
		}
		return users;
	}

	public static UserDTO createUserDTO(int noOfAddress) {

		UserDTO userEntity = new UserDTO();
		Faker faker = new Faker();
		Address address = faker.address();
		userEntity.setUserName(address.firstName());
		userEntity.setFirstName(address.firstName());
		userEntity.setLastName(address.lastName());
		userEntity.setMobileno(faker.phoneNumber().cellPhone());
		userEntity.setPassword(faker.bothify("??####???@"));
		userEntity.setEmail(faker.bothify("????##@gmail.com"));
		userEntity.setAddress(createAddressDTOs(noOfAddress));
		return userEntity;

	}

	public static List<AddressDTO> createAddressDTOs(int size) {
		List<AddressDTO> addresses = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			addresses.add(createAddressDTO());
		}
		return addresses;
	}

	public static AddressDTO createAddressDTO() {

		AddressDTO address = new AddressDTO();
		ArrayList<String> types = new ArrayList<>(Arrays.asList("billing", "personal"));

		Faker faker = new Faker();
		Address fakeAddress = faker.address();
		address.setCity(fakeAddress.city());
		address.setCountry(fakeAddress.country());
		address.setPincode(fakeAddress.countryCode());
		address.setStreetName(fakeAddress.streetName());
		address.setId(faker.bothify("???###?#?#??##"));
		address.setType(types.get(new Random().nextInt(types.size())));
		return address;

	}

}
