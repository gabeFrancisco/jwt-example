package com.ellyon.jwt_example.config;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ellyon.jwt_example.model.User;

@Component
public class TokenConfig {

	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(User user) {
		return JWT.create().withClaim("userId", user.getId().toString())
				.withSubject(user.getEmail())
				.withExpiresAt(Instant.now().plusSeconds(3600))
				.withIssuedAt(Instant.now()).sign(Algorithm.HMAC256(secret));
	}

	public Optional<JWTUserData> validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			DecodedJWT decode = JWT.require(algorithm).build().verify(token);

			return Optional.of(JWTUserData.builder()
					.userId(decode.getClaim("userId").as(UUID.class))
					.email(decode.getSubject()).build());
		} catch (JWTVerificationException ex) {
			return Optional.empty();
		}
	}
}
