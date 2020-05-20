package com.registration.validation;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.LowercaseCharacterRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;

import com.registration.annotation.ValidPassword;

public class PasswordStrengthValidator implements ConstraintValidator<ValidPassword, String> {

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {

		PasswordValidator validator = new PasswordValidator(Arrays.asList(
				// at least 8 characters
				new LengthRule(8, 30),

				// at least one upper-case character
				new UppercaseCharacterRule(1),

				// at least one lower-case character
				new LowercaseCharacterRule(1),

				// at least one digit character
				new DigitCharacterRule(1),

				// at least one symbol (special character)
				new SpecialCharacterRule(1),

				// no whitespace
				new WhitespaceRule()

		));

		RuleResult result = validator.validate(new PasswordData(password));
		if (result.isValid()) {
			return true;
		}
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(
				validator.getMessages(result).stream().collect(Collectors.joining(","))).addConstraintViolation();
		return false;

	}

}
