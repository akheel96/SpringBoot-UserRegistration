package com.registration.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.registration.constants.ErrorCodes;
import com.registration.exception.model.ErrorResource;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(value = { BadRequestException.class })
	public ResponseEntity<Object> handleBadRequestException(BadRequestException e, WebRequest request) {
		ErrorResource error = new ErrorResource(ErrorCodes.VALIDATION_ERROR_CODE.getErrorCode(), e.getMessage());
		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { NotFoundException.class })
	public ResponseEntity<Object> handleNotFoundException(NotFoundException e, WebRequest request) {
		ErrorResource error = new ErrorResource(ErrorCodes.VALIDATION_ERROR_CODE.getErrorCode(), e.getMessage());
		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}
