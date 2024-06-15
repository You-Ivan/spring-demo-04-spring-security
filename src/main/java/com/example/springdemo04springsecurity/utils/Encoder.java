package com.example.springdemo04springsecurity.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encoder {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static String encode(String rawStr) {
        return passwordEncoder.encode(rawStr);
    }

    public static boolean verify(String rawString, String hashedStr) {
        return passwordEncoder.matches(rawString, hashedStr);
    }
}
