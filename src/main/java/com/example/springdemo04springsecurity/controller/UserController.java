package com.example.springdemo04springsecurity.controller;

import com.example.springdemo04springsecurity.dto.UserDTO;
import com.example.springdemo04springsecurity.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
            return "Login Success!";
        }
        return "Login failed! Please check your username and password!";
    }
}
