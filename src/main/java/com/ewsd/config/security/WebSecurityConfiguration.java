package com.ewsd.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;

	@Autowired
	public WebSecurityConfiguration(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		this.passwordEncoder = passwordEncoder;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		// auth.inMemoryAuthentication().withUser("mariom").password("{noop}secret").roles("ADMIN");
		// auth.inMemoryAuthentication().withUser("samiha").password("{noop}secret").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/img/**","/uploaded_img/**", "/css/**", "/js/**", "/vendor/**","/register/**","/resetPassword/**", "/scss/**")
			.permitAll()
		.and()
			.authorizeRequests()
			.antMatchers("/player/**")
			.hasAnyRole("ADMIN")
			.antMatchers("/test/**")
			.hasAnyRole("ADMIN")
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/login") 														
			.loginProcessingUrl("/login-processing") 
			.permitAll()
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error=true") 												
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");
	}
}
