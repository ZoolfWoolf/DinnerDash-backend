package com.dinnerdash.backend.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestAuthController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('CUSTOMER') or hasRole(RESTAURANT)")
	public String userAccess() {
		return "Global Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('RESTAURANT')")
	public String moderatorAccess() {
		return "Restaurant Board.";
	}
	@GetMapping("/admin")
	@PreAuthorize("hasRole('CUSTOMER')")
	public String adminAccess() {
		return "Customer Board.";
	}
}