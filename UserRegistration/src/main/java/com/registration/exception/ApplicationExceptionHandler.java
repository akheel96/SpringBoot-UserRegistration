package com.registration.exception;

import java.util.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.registration.constants.ErrorCodes;
import com.registration.exception.model.ErrorResource;
import com.registration.exception.model.ValidationException;

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

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
			WebRequest request) {
		ValidationException error = new ValidationException(ErrorCodes.VALIDATION_ERROR_CODE.getErrorCode());
		
		List<String> errors = new ArrayList<>();
		e.getBindingResult().getFieldErrors().forEach(err -> errors.add(err.getDefaultMessage()));
		error.setErrors(errors);
		
		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
