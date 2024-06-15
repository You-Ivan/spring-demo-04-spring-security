package com.example.springdemo04springsecurity;

import com.example.springdemo04springsecurity.model.User;
import com.example.springdemo04springsecurity.repository.UserRepo;
import com.example.springdemo04springsecurity.utils.Encoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDemo04SpringSecurityApplicationTests {
    @Autowired
    UserRepo userRepo;

    @Test
    void contextLoads() {
        userRepo.save(User.builder()
                .username("Ivan")
                .password(Encoder.encode("1234"))
                .build());
    }

}
