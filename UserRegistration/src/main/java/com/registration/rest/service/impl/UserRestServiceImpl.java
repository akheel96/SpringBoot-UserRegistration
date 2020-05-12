package com.registration.rest.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.registration.entity.model.User;
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
		List<User> users = userRepository.findAll();
		return users.stream().map(this::toUserDTO).collect(Collectors.toList());
	}

	@Override
	public UserDTO addUser(UserDTO userDTO) {
		UUID uuid = UUID.randomUUID();
		userDTO.setId(uuid.toString().replace("-", ""));
		userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		User savedUser = userRepository.save(toUser(userDTO));
		return toUserDTO(savedUser);
	}

	@Override
	public UserDTO getUserByUserName(String userName) {
		User user = userRepository.findByUserName(userName);
		return user == null ? null : toUserDTO(user);
	}

	private UserDTO toUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

	private User toUser(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
