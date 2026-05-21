package com.inventory.order.test.infrastructure.adapters.input.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.order.test.application.dto.AuthResponse;
import com.inventory.order.test.application.dto.LoginRequest;
import com.inventory.order.test.application.dto.RegisterRequest;
import com.inventory.order.test.application.service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

}