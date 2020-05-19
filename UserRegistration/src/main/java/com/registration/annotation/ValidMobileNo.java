package com.registration.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.registration.validation.MobileNumberValidator;

@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = MobileNumberValidator.class)
public @interface ValidMobileNo {
	
	String message() default "Invalid Mobile Number";
	
	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
