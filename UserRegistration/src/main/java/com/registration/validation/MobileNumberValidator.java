package com.registration.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.registration.annotation.ValidMobileNo;
import com.registration.utils.ValidationUtil;

public class MobileNumberValidator implements ConstraintValidator<ValidMobileNo, String>{

	@Override
	public void initialize(final ValidMobileNo constraintAnnotation) {
	}

	@Override
	public boolean isValid(String mobileNo, ConstraintValidatorContext context) {
		return ValidationUtil.validateMobileNo(mobileNo);
	}
	

}
