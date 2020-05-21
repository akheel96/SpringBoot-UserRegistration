package com.registration.security;

import com.registration.app.ApplicationProperties;
import com.registration.app.SpringApplicationContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 8640000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	public static String getTokenSecret() {
		ApplicationProperties appProperties = (ApplicationProperties) SpringApplicationContext.getBean("applicationProperties");
		return appProperties.getTokenSecret();
	}
	
	public static String getVerificationTokenSecret() {
		ApplicationProperties appProperties = (ApplicationProperties) SpringApplicationContext.getBean("applicationProperties");
		return appProperties.getTokenSecret();
	}
}
