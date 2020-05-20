package com.registration.app.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.registration.app.event.OnRegistrationCompleteEvent;
import com.registration.constants.Endpoints;
import com.registration.rest.model.response.UserResponseModel;
import com.registration.security.jwt.JwtTokenProvider;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.sendVerificationMail(event);
	}

	private void sendVerificationMail(OnRegistrationCompleteEvent event) {
		UserResponseModel user = event.getUser();
		String token = JwtTokenProvider.getVerificationJwtToken(user.getUserName());
		String recepientEmail = user.getEmail();
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recepientEmail);
		email.setSubject("Registration Confirmation");
		email.setText("Confirm Your Registration. URL: "
				+ String.format(Endpoints.DEFAULT_HOST + Endpoints.VERIFY_EMAIL_ENDPOINT_WITH_TOKEN, token));
		javaMailSender.send(email);

	}

}
