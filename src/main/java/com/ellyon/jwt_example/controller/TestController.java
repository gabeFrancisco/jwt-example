package com.ellyon.jwt_example.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api/tests")
public class TestController {

    @GetMapping
    public String get() {
        return "God bless you!";
    }

}
