package com.ellyon.jwt_example.config;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ellyon.jwt_example.model.User;

@Component
public class TokenConfig {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user){
        return JWT.create()
            .withClaim("userId", user.getId().toString())
            .withSubject(user.getEmail())
            .withExpiresAt(Instant.now().plusSeconds(3600))
            .withIssuedAt(Instant.now())
            .sign(Algorithm.HMAC256(secret));
    }
}
