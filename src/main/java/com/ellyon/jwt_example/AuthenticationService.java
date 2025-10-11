package com.ellyon.jwt_example;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public String authenticate(){
        return "Token";
    }
}
