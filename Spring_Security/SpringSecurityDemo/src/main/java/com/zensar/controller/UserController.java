package com.zensar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.UserDto;

@RestController
@RequestMapping("/demoapp")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping(value="/login", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> authenticate(@RequestBody UserDto userDto) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userDto.getUsername(), userDto.getPassword()
					));
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		catch(AuthenticationException e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/all")
	public String getAll() { //Public API
		return "Hi All";
	}
	@GetMapping("/user")
	public String getUser() { //For USER, ADMIN role
		return "Hi User";
	}
	@GetMapping("/admin")
	public String getAdmin() { //Only for ADMIN
		return "Hi Admin";
	}
}
