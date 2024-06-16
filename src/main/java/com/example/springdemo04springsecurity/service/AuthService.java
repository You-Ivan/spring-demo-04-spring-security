package com.example.springdemo04springsecurity.service;

import com.example.springdemo04springsecurity.model.User;
import com.example.springdemo04springsecurity.repository.UserRepo;
import com.example.springdemo04springsecurity.utils.Encoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepo userRepo;

    public AuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean register(String username, String password) {
        if (userRepo.findByUsername(username).isEmpty()) {
            User newUser = User.builder()
                    .username(username)
                    .password(Encoder.encode(password))
                    .build();
            userRepo.save(newUser);
            return true;
        } else {
            return false;
        }
    }

    public boolean login(String username, String password) {
        if (userRepo.findByUsername(username).isPresent()) {
            User user = userRepo.findByUsername(username).get();
            return Encoder.verify(password, user.getPassword());
        } else {
            return false;
        }
    }
}
