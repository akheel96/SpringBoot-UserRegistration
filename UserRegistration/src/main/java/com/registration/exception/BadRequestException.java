package com.registration.exception;

public class BadRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6961257277067514047L;

	public BadRequestException(String message) {
		super(message);
	}
}
