package com.ellyon.jwt_example.config;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	private final TokenConfig config;

	public SecurityFilter(TokenConfig config) {
		this.config = config;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");

		if (Strings.isNotEmpty(authHeader) && authHeader.startsWith("Bearer ")) {

			String token = authHeader.substring("Bearer ".length());
			Optional<JWTUserData> optUser = config.validateToken(token);

			if(optUser.isPresent()){
				JWTUserData user = optUser.get();
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken (user, null, null);

				SecurityContextHolder.getContext().setAuthentication(authentication);

			}
			filterChain.doFilter(request, response);
		}
		else{
			filterChain.doFilter(request, response);
		}
	}

}
