package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/home")
public class HomeController {
    @GetMapping
    public String getMessage(){
        return "Hello, User-101 Welcome to my home page.";
    }
}
