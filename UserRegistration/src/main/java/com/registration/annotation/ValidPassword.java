package com.registration.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.registration.validation.PasswordStrengthValidator;

@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = PasswordStrengthValidator.class)
public @interface ValidPassword {

	String message() default "Invalid Password. \n Password should contain: 1 UpperCase, 1 LowerCase, 1 Number, 1 Special Character";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
