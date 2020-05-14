package com.registration.constants;

public enum ErrorMessages {

	USER_NOT_FOUND("User Not Found"),
	USER_ALREADY_EXISTS("User Already Exists");
	
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
