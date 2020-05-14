package com.registration.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.registration.entity.model.UserEntity;
import com.registration.entity.repository.UserRepository;
import com.registration.rest.model.UserDTO;
import com.registration.rest.service.UserRestService;

@Service
public class UserRestServiceImpl implements UserRestService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<UserDTO> getUsers() {
		List<UserEntity> users = userRepository.findAll();
		return users.stream().map(this::toUserDTO).collect(Collectors.toList());
	}

	@Override
	public UserDTO addUser(UserDTO userDTO) {
		UUID uuid = UUID.randomUUID();
		userDTO.setId(uuid.toString().replace("-", ""));
		userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		UserEntity savedUser = userRepository.save(toUser(userDTO));
		return toUserDTO(savedUser);
	}

	@Override
	public UserDTO getUserByUserName(String userName) {
		UserEntity user = userRepository.findByUserName(userName);
		return user == null ? null : toUserDTO(user);
	}

	private UserDTO toUserDTO(UserEntity user) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

	private UserEntity toUser(UserDTO userDTO) {
		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(userDTO, user);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUserName(username);
		return user == null ? null : new User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}

}
