package com.zensar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserDetailsService userDetailsService;
	
	//AuthenticationManager
	@Bean
	public AuthenticationManager getAuthenticationManager() {
		ProviderManager authenticationManager = new ProviderManager(getAuthenticationProvider());
		return authenticationManager;
	}
	
	//Authorization
	@Bean
	public SecurityFilterChain authorization(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf->csrf.disable())
			.authorizeHttpRequests(auth->
				auth
				.requestMatchers("/demoapp/admin").hasRole("ADMIN")
				.requestMatchers("/demoapp/user").hasAnyRole("ADMIN", "USER")
				.requestMatchers("/demoapp/all", "/swagger-ui/**", "/v3/api-docs/**", "/demoapp/login").permitAll()
				.anyRequest().authenticated()
			).formLogin(Customizer.withDefaults());
		return httpSecurity.build();
	}
	
	//Authentication
	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider =
				new DaoAuthenticationProvider(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return daoAuthenticationProvider;
	}
	
	//In memory users
	//@Bean
	public UserDetailsService buildUsers() {
		UserBuilder userBuilder = User.builder();
		UserDetails tomUser = userBuilder.username("tom").password(passwordEncoder.encode("tom123")).build();
		UserDetails jerryUser = userBuilder.username("jerry").password(passwordEncoder.encode("jerry123")).build();
		UserDetailsService userDetailsService = new InMemoryUserDetailsManager(tomUser, jerryUser);
		return userDetailsService;
	}
}
