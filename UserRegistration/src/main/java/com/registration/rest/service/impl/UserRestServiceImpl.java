package com.registration.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.registration.entity.model.UserEntity;
import com.registration.entity.repository.UserRepository;
import com.registration.rest.model.UserDTO;
import com.registration.rest.service.UserRestService;
import com.registration.utils.EntityDtoMappingUtil;

@Service
public class UserRestServiceImpl implements UserRestService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<UserDTO> getUsers(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		Page<UserEntity> users = userRepository.findAll(pageable);
		return users.stream().map(EntityDtoMappingUtil::toUserDTO).collect(Collectors.toList());
	}

	@Override
	public UserDTO addUser(UserDTO userDTO) {
		userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		userDTO.getAddress().forEach(address -> {
			address.setId(UUID.randomUUID().toString().replace("-", ""));
			address.setUser(userDTO);
		});
		UserEntity savedUser = userRepository.save(EntityDtoMappingUtil.toUserEntity(userDTO));
		return EntityDtoMappingUtil.toUserDTO(savedUser);
	}

	@Override
	public UserDTO getUserByUserName(String userName) {
		UserEntity user = userRepository.findByUserName(userName);
		return user == null ? null : EntityDtoMappingUtil.toUserDTO(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUserName(username);
		if (user != null && user.getVerificationStatus()) {
			return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
		}
		return null;
	}

	@Override
	public UserDTO updateUser(String userName, UserDTO userDTO) {
		UserEntity updatedUser = userRepository.save(EntityDtoMappingUtil.toUserEntity(userDTO));
		return EntityDtoMappingUtil.toUserDTO(updatedUser);
	}

	@Override
	public void deleteUser(UserDTO user) {
		UserEntity userEntity = EntityDtoMappingUtil.toUserEntity(user);
		userRepository.delete(userEntity);
	}

	@Override
	public UserDTO getUserByEmail(String email) {
		UserEntity user = userRepository.findByEmail(email);
		return user != null ? EntityDtoMappingUtil.toUserDTO(user) : null;
	}

}
