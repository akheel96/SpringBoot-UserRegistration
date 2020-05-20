package com.registration.constants;

public enum ErrorMessages {

	USER_NOT_FOUND("User Not Found"),
	USER_ALREADY_EXISTS("User Already Exists"),
	PROVIDE_USER_DETAILS("Provide User Details."),
	INVALID_TOKEN("Invalid Jwt Token"),
	ADDRESS_NOT_FOUND("Address not found");
	
	private String errorMessage;
	
	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
