package com.ellyon.jwt_example.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import org.springframework.stereotype.Service;

import com.ellyon.jwt_example.model.User;
import com.ellyon.jwt_example.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String hashPassword(String password){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public String login(String email, String password){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found!"));

        if(user.getPassword().equals(hashPassword(password))){
            return "";
        }

        throw new RuntimeException("Invalid credentials!");
    }
}
