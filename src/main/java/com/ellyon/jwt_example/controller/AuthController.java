package com.ellyon.jwt_example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ellyon.jwt_example.dto.LoginRequest;
import com.ellyon.jwt_example.dto.LoginResponse;
import com.ellyon.jwt_example.dto.RegisterUserRequest;
import com.ellyon.jwt_example.dto.RegisterUserResponse;
import com.ellyon.jwt_example.model.User;
import com.ellyon.jwt_example.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request){
        User user = new User();

        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(
            user.getUsername(),
            user.getEmail()
        ));
    }
}
