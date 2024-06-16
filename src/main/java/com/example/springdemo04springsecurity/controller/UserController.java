package com.example.springdemo04springsecurity.controller;

import com.example.springdemo04springsecurity.dto.MyUserDetails;
import com.example.springdemo04springsecurity.service.AuthService;
import com.example.springdemo04springsecurity.utils.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public UserController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody MyUserDetails userDetails) {
        if (userDetails.getPassword().isEmpty() || userDetails.getUsername().isEmpty()) {
            return "Username and password cannot be empty!";
        }
        if (authService.register(userDetails.getUsername(), userDetails.getPassword())) {
            return "Register success!";
        } else {
            return "This username has already been taken!";
        }
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody MyUserDetails userDetails) {
        if (userDetails.getUsername().isEmpty() || userDetails.getPassword().isEmpty()) {
            return "Username and password cannot be empty!";
        }
        if (authService.login(userDetails.getUsername(), userDetails.getPassword())) {
            String token = jwtUtil.generateToken(userDetails.getUsername());
            return "Login Success!" + "token: " + token;
        }
        return "Login failed! Please check your username and password!";
    }
}
