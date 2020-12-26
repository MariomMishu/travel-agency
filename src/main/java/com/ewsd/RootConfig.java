package com.ewsd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ewsd.config.persistence.HibernateConfig;

@ComponentScan(basePackages = {
		"com.ewsd.service",
		"com.ewsd.config.persistence",
		"com.ewsd.config.security" })
public class RootConfig {
	@Bean
	GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Bean
	HibernateConfig hibernateConfig() {
		return new HibernateConfig();
	}
	@Bean
	PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
	}
}
