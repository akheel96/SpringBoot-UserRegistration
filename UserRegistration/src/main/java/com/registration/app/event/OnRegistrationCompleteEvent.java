package com.registration.app.event;

import org.springframework.context.ApplicationEvent;

import com.registration.rest.model.response.UserResponseModel;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 64571450994878326L;

	private UserResponseModel user;

	public OnRegistrationCompleteEvent(UserResponseModel user) {
		super(user);
		this.user = user;
	}

	public UserResponseModel getUser() {
		return user;
	}

	public void setUser(UserResponseModel user) {
		this.user = user;
	}
}
