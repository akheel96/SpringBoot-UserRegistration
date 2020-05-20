package com.registration.constants;

public class Endpoints {

	/*
	 * User endpoints
	 */
	public static final String SIGNUP_ENDPOINT = "/users";
	public static final String SIGNIN_ENDPOINT = "/login";

	public static final String DEFAULT_HOST = "http://localhost:8080";

	public static final String VERIFY_EMAIL_ENDPOINT = "/verify-user";
	public static final String VERIFY_EMAIL_ENDPOINT_WITH_TOKEN = VERIFY_EMAIL_ENDPOINT + "?token=%s";
}
