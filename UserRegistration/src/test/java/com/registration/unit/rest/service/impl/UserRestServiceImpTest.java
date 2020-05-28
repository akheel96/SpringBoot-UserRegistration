package com.registration.unit.rest.service.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.registration.entity.model.UserEntity;
import com.registration.entity.repository.UserRepository;
import com.registration.rest.model.UserDTO;
import com.registration.rest.service.impl.UserRestServiceImpl;
import com.registration.test.utils.AssertionUtil;
import com.registration.test.utils.DataGenerationUtil;
import com.registration.utils.EntityDtoMappingUtil;

class UserRestServiceImpTest {

	@Mock
	UserRepository userRepository;
	
	@Mock
	BCryptPasswordEncoder encoder;

	@InjectMocks
	UserRestServiceImpl userRestService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetUserByUserName_Valid() {

		UserEntity userEntity = DataGenerationUtil.createUserEntity(2);

		when(userRepository.findByUserName(userEntity.getUserName())).thenReturn(userEntity);

		UserDTO userDTO = userRestService.getUserByUserName(userEntity.getUserName());

		AssertionUtil.assertUserDetails(userEntity, userDTO);

	}

	@Test
	public void testGetUserByUserName_UserNotFound() {
		when(userRepository.findByUserName("akheel")).thenReturn(null);
		UserDTO userDTO = userRestService.getUserByUserName("akheel");
		assertNull(userDTO);
	}

	@Test
	public void testGetUsers() {
		List<UserEntity> userEntities = DataGenerationUtil.createUserEntities(3, 2);
		when(userRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<UserEntity>(userEntities));
		List<UserDTO> userDtos = userRestService.getUsers(0, 3);
		userDtos.forEach(dto -> AssertionUtil.assertUserDetails(userEntities.stream()
				.filter(entity -> entity.getUserName().equals(dto.getUserName())).findAny().orElse(null), dto));

	}

	@Test
	public void testUpdateUser() {

		UserDTO userToBeUpdated = DataGenerationUtil.createUserDTO(2);

		UserEntity userEntityToBeUpdated = EntityDtoMappingUtil.toUserEntity(userToBeUpdated);
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntityToBeUpdated);

		UserDTO updatedUser = userRestService.updateUser(userToBeUpdated);

		AssertionUtil.assertUserDetails(userToBeUpdated, updatedUser);

	}

	@Test
	public void testGetUserByEmail() {
		UserEntity userEntity = DataGenerationUtil.createUserEntity(2);
		when(userRepository.findByEmail(userEntity.getEmail())).thenReturn(userEntity);

		UserDTO userDTO = userRestService.getUserByEmail(userEntity.getEmail());

		AssertionUtil.assertUserDetails(userEntity, userDTO);

	}
	
	@Test
	public void testGetUserByEmail_NotFound() {
		when(userRepository.findByEmail(any())).thenReturn(null);

		UserDTO userDTO = userRestService.getUserByEmail("akheel@gmail.com");

		assertNull(userDTO);

	}

	@Test
	public void testAddUser() {

		UserDTO userToBeAdded = DataGenerationUtil.createUserDTO(2);

		UserEntity userEntityToBeAdded = EntityDtoMappingUtil.toUserEntity(userToBeAdded);
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntityToBeAdded);
		when(encoder.encode(any())).thenReturn(userToBeAdded.getPassword());

		UserDTO addedUser = userRestService.addUser(userToBeAdded);

		AssertionUtil.assertUserDetails(userToBeAdded, addedUser);

	}

}
