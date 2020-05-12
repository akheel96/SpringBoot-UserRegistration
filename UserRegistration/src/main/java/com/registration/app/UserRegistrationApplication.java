package com.registration.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.registration"})
@EnableJpaRepositories("com.registration")
@EntityScan("com.registration")
@SpringBootApplication
public class UserRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationApplication.class, args);
	}

}
