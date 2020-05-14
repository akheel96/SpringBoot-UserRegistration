package com.registration.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration.app.SpringApplicationContext;
import com.registration.rest.model.request.UserLoginRequestDTO;
import com.registration.rest.service.UserRestService;
import com.registration.security.jwt.JwtTokenProvider;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			UserLoginRequestDTO credentials = new ObjectMapper().readValue(request.getInputStream(),
					UserLoginRequestDTO.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUserName(),
					credentials.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) {

		String username = ((User) auth.getPrincipal()).getUsername();

		String token = JwtTokenProvider.getJwtToken(username);

		UserRestService userService = (UserRestService) SpringApplicationContext.getBean("userRestServiceImpl");
		response.addHeader("UserID", userService.getUserByUserName(username).getId());
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);

	}

}
