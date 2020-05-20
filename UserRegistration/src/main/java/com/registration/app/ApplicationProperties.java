package com.registration.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
	
	@Autowired
	private Environment env;
	
	public String getTokenSecret() {
		return env.getProperty("security.token.secret");
	}
	
	public String getVerificationTokenSecret() {
		return env.getProperty("security.verification.token.secret");
	}
	
	

}
