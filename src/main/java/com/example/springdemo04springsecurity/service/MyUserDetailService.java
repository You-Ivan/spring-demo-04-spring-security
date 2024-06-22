package com.example.springdemo04springsecurity.service;

import com.example.springdemo04springsecurity.model.User;
import com.example.springdemo04springsecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;

    public MyUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = optionalUser.get();
        org.springframework.security.core.userdetails.User.UserBuilder userDetailBuilder = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword());
        if (user.getUsername().equals("Ivan")) {
            return userDetailBuilder
                    .authorities("ROLE_ADMIN")
                    .build();
        }
        return userDetailBuilder
                .authorities("ROLE_USER")
                .build();
    }
}
