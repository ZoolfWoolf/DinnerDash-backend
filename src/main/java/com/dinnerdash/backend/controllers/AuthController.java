package com.dinnerdash.backend.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinnerdash.backend.models.Customer;
import com.dinnerdash.backend.models.ERoles;
import com.dinnerdash.backend.models.Restaurant;
import com.dinnerdash.backend.models.Roles;
import com.dinnerdash.backend.models.Users;
import com.dinnerdash.backend.payload.request.CustomerSignupRequest;
import com.dinnerdash.backend.payload.request.LoginRequest;
import com.dinnerdash.backend.payload.request.RestaurantSignupRequest;
import com.dinnerdash.backend.payload.response.JwtResponse;
import com.dinnerdash.backend.payload.response.MessageResponse;
import com.dinnerdash.backend.repositories.CustomerRepository;
import com.dinnerdash.backend.repositories.RestaurantRepository;
import com.dinnerdash.backend.repositories.RoleRepository;
import com.dinnerdash.backend.repositories.UserRepository;
import com.dinnerdash.backend.security.jwt.JwtUtils;
import com.dinnerdash.backend.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	CustomerRepository customer;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RestaurantRepository restaurant;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));
	}

	@PostMapping("/customerSignup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody CustomerSignupRequest signUpRequest) {
		ResponseEntity<?> checkIfAlreadyExists = signupHelper(signUpRequest.getUsername(), signUpRequest.getEmail());
		if (checkIfAlreadyExists != null) {
			return checkIfAlreadyExists;
		}

		// Making customer
		Customer temp = new Customer(0, signUpRequest.getWalletAmount(), signUpRequest.getPhoneNumber());
		Users user = userMaker(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword(),
				ERoles.ROLE_CUSTOMER);
		userRepository.save(user);
		temp.setCustomerID(user.getId());
		customer.save(temp);
		return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
	}

	@PostMapping("/restaurantSignup")
	public ResponseEntity<?> registerRestaurant(@Valid @RequestBody RestaurantSignupRequest signUpRequest) {
		ResponseEntity<?> checkIfAlreadyExists = signupHelper(signUpRequest.getUsername(), signUpRequest.getEmail());
		if (checkIfAlreadyExists != null) {
			return checkIfAlreadyExists;
		}

		// Making restaurant.
		Restaurant temp = new Restaurant(0, signUpRequest.getName(), signUpRequest.getTheme(), signUpRequest.getUrl());
		Users user = userMaker(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword(),
				ERoles.ROLE_RESTAURANT);
		userRepository.save(user);
		temp.setRestaurantId(user.getId());
		restaurant.save(temp);
		return ResponseEntity.ok(new MessageResponse("Restaurant registered successfully!"));
	}

	private ResponseEntity<?> signupHelper(String username, String email) {
		if (userRepository.existsByUsername(username)) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(email)) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		return null;
	}

	private Users userMaker(String username, String email, String password, ERoles role) {
		// Create new user's account
		Users user = new Users(username,
				email,
				encoder.encode(password));

		Set<Roles> roles = new HashSet<>();
		Roles restaurantRole = roleRepository.findByName(role)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));

		roles.add(restaurantRole);
		user.setRoles(roles);
		return user;
	}
}