package com.AnkitIndia.jwtauthentication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestAPIs {

	@GetMapping("/AnkitIndia/user")
//	@GetMapping("/MeghnaBidi/user")
//	@GetMapping("/MeghanaPanMasala/user")
//	@GetMapping("/IndianSpices/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return ">>> User Contents!";
	}

	@GetMapping("/AnkitIndia/pm")
//	@GetMapping("/MeghnaBidi/pm")
//	@GetMapping("/MeghanaPanMasala/pm")
	//@GetMapping("/IndianSpices/pm")
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	public String projectManagementAccess() {
		return ">>> Board Management Project";
	}
	
	@GetMapping("/AnkitIndia/admin")
//	@GetMapping("/MeghnaBidi/admin")
//	@GetMapping("/MeghanaPanMasala/admin")
	//@GetMapping("/IndianSpices/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Contents";
	}
}