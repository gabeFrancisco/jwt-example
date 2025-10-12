package com.ellyon.jwt_example;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ellyon.jwt_example.controllers.JwtService;

@Service
public class AuthenticationService {
    private final JwtService jwtService;

    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }
}
