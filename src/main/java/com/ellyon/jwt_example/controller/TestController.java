package com.ellyon.jwt_example.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController("api/tests")
public class TestController {

    @GetMapping
    public String get() {
        return "God bless you!";
    }

}
