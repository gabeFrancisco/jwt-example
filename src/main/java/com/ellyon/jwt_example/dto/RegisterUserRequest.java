package com.ellyon.jwt_example.dto;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(
    @NotEmpty(message = "Name cannot be null!") String username,
    @NotEmpty(message = "Email cannot be null!") String email,
    @NotEmpty(message = "Password cannot be null!") String password
) {

}
