package com.zensar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	//In memory users
	@Bean
	public UserDetailsService buildUsers() {
		UserBuilder userBuilder = User.builder();
		UserDetails tomUser = userBuilder.username("tom").password(passwordEncoder.encode("tom123")).build();
		UserDetails jerryUser = userBuilder.username("jerry").password(passwordEncoder.encode("jerry123")).build();
		UserDetailsService userDetailsService = new InMemoryUserDetailsManager(tomUser, jerryUser);
		return userDetailsService;
	}
}
