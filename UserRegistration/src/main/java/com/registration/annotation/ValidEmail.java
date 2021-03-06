package com.registration.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.registration.validation.EmailValidator;

@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmail {
	
	String message() default "Invalid Email";
	
	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
