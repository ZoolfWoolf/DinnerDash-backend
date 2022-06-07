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
import com.dinnerdash.backend.payload.request.LoginRequest;
import com.dinnerdash.backend.payload.request.SignupRequest;
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
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		// Create new user's account
		Users user = new Users(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Roles> roles = new HashSet<>();
		if (strRoles == null) {
			Roles userRole = roleRepository.findByName(ERoles.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "restaurant":
					Roles restaurantRole = roleRepository.findByName(ERoles.ROLE_RESTAURANT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(restaurantRole);
					break;
				case "customer":
					Roles userRole = roleRepository.findByName(ERoles.ROLE_CUSTOMER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
					break;
				default :
				}
			});
		}
		
		user.setRoles(roles);
		userRepository.save(user);
		ERoles temp = roles.iterator().next().getName();

		if (temp == ERoles.ROLE_CUSTOMER){
			customer.save(new Customer(user.getId(), 1000, "03004244221"));
		}
		else if (temp == ERoles.ROLE_RESTAURANT){
			restaurant.save(new Restaurant(user.getId(), "Butt Karahi", "Black", "wow.com"));
		}
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}