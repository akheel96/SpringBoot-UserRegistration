package com.registration.constants;

public enum ErrorCodes {

	VALIDATION_ERROR_CODE("error.validation");
	
	private String errorCode;

	private ErrorCodes(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
