package com.registration.unit.rest.manager;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.registration.exception.BadRequestException;
import com.registration.exception.NotFoundException;
import com.registration.rest.manager.UserRestManager;
import com.registration.rest.model.UserDTO;
import com.registration.rest.model.request.UserSignUpRequestModel;
import com.registration.rest.model.response.UserResponseModel;
import com.registration.rest.service.UserRestService;
import com.registration.test.utils.AssertionUtil;
import com.registration.test.utils.DataGenerationUtil;
import com.registration.utils.EntityDtoMappingUtil;

class UserRestManagerTest {

	@Mock
	UserRestService userRestService;

	@InjectMocks
	UserRestManager userRestManager;

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testCreateUser() {
		UserDTO userDTO = DataGenerationUtil.createUserDTO(2);
		UserSignUpRequestModel userToBeCreated = EntityDtoMappingUtil.toUserSignUpRequestModel(userDTO);
		when(userRestService.addUser(any(UserDTO.class))).thenReturn(userDTO);

		UserResponseModel createdUser = userRestManager.createUser(userToBeCreated);
		AssertionUtil.assertUserDetails(createdUser, userDTO);
	}

	@Test
	void testCreateUser_NullPayload() {
		assertThrows(BadRequestException.class, () -> userRestManager.createUser(null));
	}

	@Test
	void testCreateUser_ExistingEmail() {
		when(userRestService.getUserByEmail("akheel@gmail.com")).thenReturn(new UserDTO());
		assertThrows(BadRequestException.class, () -> {
			UserSignUpRequestModel userRequest = new UserSignUpRequestModel();
			userRequest.setEmail("akheel@gmail.com");
			userRestManager.createUser(userRequest);
		});
	}

	@Test
	void testCreateUser_ExistingUserName() {
		when(userRestService.getUserByUserName("akheel")).thenReturn(new UserDTO());
		assertThrows(BadRequestException.class, () -> {
			UserSignUpRequestModel userRequest = new UserSignUpRequestModel();
			userRequest.setUserName("akheel");
			userRestManager.createUser(userRequest);
		});
	}

	@Test
	void testGetUser() {
		UserDTO userDTO = DataGenerationUtil.createUserDTO(2);
		when(userRestService.getUserByUserName(userDTO.getUserName())).thenReturn(userDTO);
		UserResponseModel userResponse = userRestManager.getUser(userDTO.getUserName());
		AssertionUtil.assertUserDetails(userResponse, userDTO);
	}

	@Test
	void testGetUser_NotFound() {
		when(userRestService.getUserByUserName(any())).thenReturn(null);
		assertThrows(NotFoundException.class, () -> userRestManager.getUser("akheel"));
	}

	@Test
	void testGetUsers() {
		List<UserDTO> userDTOs = DataGenerationUtil.createUserDTOs(2, 2);
		when(userRestService.getUsers(anyInt(), anyInt())).thenReturn(userDTOs);
		List<UserResponseModel> users = userRestManager.getUsers(2, 2);
		userDTOs.forEach(dto -> AssertionUtil.assertUserDetails(
				users.stream().filter(user -> user.getUserName().equals(dto.getUserName())).findAny().orElse(null),
				dto));
	}

	@Test
	void testUpdateUser() {
		UserDTO userDTO = DataGenerationUtil.createUserDTO(2);

		when(userRestService.getUserByUserName(userDTO.getUserName())).thenReturn(userDTO);
		when(userRestService.updateUser(any(UserDTO.class))).thenReturn(userDTO);

		UserSignUpRequestModel detailsToBeUpdated = EntityDtoMappingUtil.toUserSignUpRequestModel(userDTO);
		detailsToBeUpdated.setFirstName("Batman");
		detailsToBeUpdated.setLastName("Bruce");
		detailsToBeUpdated.setMobileno("123456789");

		UserResponseModel updatedUser = userRestManager.updateUser(userDTO.getUserName(), detailsToBeUpdated);
		AssertionUtil.assertUserDetails(updatedUser, userDTO);
	}

	@Test
	void testUpdateUser_NotFound() {

		when(userRestService.getUserByUserName("akheel")).thenReturn(null);
		assertThrows(NotFoundException.class,
				() -> userRestManager.updateUser("akheel", any(UserSignUpRequestModel.class)));
	}

	@Test
	void testDeleteUser() {
		UserDTO user = DataGenerationUtil.createUserDTO(2);
		when(userRestService.getUserByUserName(user.getUserName())).thenReturn(user);
		userRestManager.deleteUser(user.getUserName());
		verify(userRestService, times(1)).deleteUser(user);
	}

	@Test
	void testDeleteUser_NotFound() {
		when(userRestService.getUserByUserName("")).thenReturn(null);
		userRestManager.deleteUser("");
		verify(userRestService, times(0)).deleteUser(any(UserDTO.class));
	}

}
