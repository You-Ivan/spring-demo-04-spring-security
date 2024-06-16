package com.example.springdemo04springsecurity.controller;

import com.example.springdemo04springsecurity.dto.UserDTO;
import com.example.springdemo04springsecurity.service.UserService;
import com.example.springdemo04springsecurity.utils.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getPassword().isEmpty() || userDTO.getUsername().isEmpty()) {
            return "Username and password cannot be empty!";
        }
        if (userService.register(userDTO.getUsername(), userDTO.getPassword())) {
            return "Register success!";
        } else {
            return "This username has already been taken!";
        }
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody UserDTO userDTO) {
        if (userDTO.getUsername().isEmpty() || userDTO.getPassword().isEmpty()) {
            return "Username and password cannot be empty!";
        }
        if (userService.login(userDTO.getUsername(), userDTO.getPassword())) {
            String token = jwtUtil.generateToken(userDTO.getUsername());
            return "Login Success!" + "token: " + token;
        }
        return "Login failed! Please check your username and password!";
    }
}
