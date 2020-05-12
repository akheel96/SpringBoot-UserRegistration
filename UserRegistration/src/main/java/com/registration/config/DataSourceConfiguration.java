package com.registration.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DataSourceConfiguration {

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	@Primary
	public DataSource getDataSource() {
		return DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/userregistration").username("root")
				.password("password").build();
	}
}
