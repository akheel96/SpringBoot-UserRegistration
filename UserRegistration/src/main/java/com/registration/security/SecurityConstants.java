package com.registration.security;

import com.registration.app.ApplicationProperties;
import com.registration.app.SpringApplicationContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 86400000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	public static String getTokenSecret() {
		ApplicationProperties appProperties = (ApplicationProperties) SpringApplicationContext.getBean("applicationProperties");
		return appProperties.getTokenSecret();
	}
}
