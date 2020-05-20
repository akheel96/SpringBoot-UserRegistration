package com.registration.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

	private static Pattern pattern;
	private static Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static boolean validateEmail(final String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean validateMobileNo(final String mobileNo) {

		if (mobileNo == null || mobileNo.isEmpty())
			return false;
		else if (mobileNo.matches("\\d{10}"))
			return true;
		// validating phone number with -, . or spaces
		else if (mobileNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
			return true;
		// validating phone number with extension length from 3 to 5
		else if (mobileNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
			return true;
		// validating phone number where area code is in braces ()
		else if (mobileNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
			return true;
		// return false if nothing matches the input
		else
			return false;
	}

}
