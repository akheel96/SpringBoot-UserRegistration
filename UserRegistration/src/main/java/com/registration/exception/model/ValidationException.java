package com.registration.exception.model;

import java.util.List;

public class ValidationException {
	
	private String code;
	private List<String> errors;
	
	public ValidationException(String code) {
		super();
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
