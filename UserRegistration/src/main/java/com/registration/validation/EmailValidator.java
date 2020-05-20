package com.registration.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.registration.annotation.ValidEmail;
import com.registration.utils.ValidationUtil;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	@Override
	public void initialize(final ValidEmail constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return ValidationUtil.validateEmail(email);
	}
}
