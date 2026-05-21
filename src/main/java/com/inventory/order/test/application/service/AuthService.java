package com.inventory.order.test.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inventory.order.test.application.dto.AuthResponse;
import com.inventory.order.test.application.dto.LoginRequest;
import com.inventory.order.test.application.dto.RegisterRequest;
import com.inventory.order.test.domain.model.Role;
import com.inventory.order.test.domain.model.User;
import com.inventory.order.test.domain.ports.UserRepositoryPort;
import com.inventory.order.test.infrastructure.adapters.output.security.JwtService;

@Service
public class AuthService {

	private final UserRepositoryPort userRepositoryPort;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public AuthService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder, JwtService jwtService) {

		this.userRepositoryPort = userRepositoryPort;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	public AuthResponse register(RegisterRequest request) {

		userRepositoryPort.findByEmail(request.getEmail()).ifPresent(user -> {
			throw new RuntimeException("Email already exists");
		});

		System.out.println("USERNAME: " + request.getUsername());
	    System.out.println("EMAIL: " + request.getEmail());
	    System.out.println("PASSWORD: " + request.getPassword());

		User user = new User(null, request.getUsername(), request.getEmail(),
				passwordEncoder.encode(request.getPassword()), Role.ROLE_USER);

		User savedUser = userRepositoryPort.save(user);

		String token = jwtService.generateToken(savedUser);

		return new AuthResponse(token);
	}

	public AuthResponse login(LoginRequest request) {

		User user = userRepositoryPort.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));

		boolean validPassword = passwordEncoder.matches(request.getPassword(), user.getPassword());

		if (!validPassword) {
			throw new RuntimeException("Invalid credentials");
		}

		String token = jwtService.generateToken(user);

		return new AuthResponse(token);
	}
}
