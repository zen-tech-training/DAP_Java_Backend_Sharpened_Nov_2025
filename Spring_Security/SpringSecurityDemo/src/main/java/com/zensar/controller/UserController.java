package com.zensar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demoapp")
public class UserController {

	@GetMapping("/all")
	public String getAll() {
		return "Hi All";
	}
	@GetMapping("/user")
	public String getUser() {
		return "Hi User";
	}
	@GetMapping("/admin")
	public String getAdmin() {
		return "Hi Admin";
	}
}
