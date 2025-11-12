package com.booky.learning.controllers;

import com.booky.learning.dtos.RegisterRequestDto;
import com.booky.learning.models.UserModel;
import com.booky.learning.repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDto registerRequestDto) {
        UserModel user = new UserModel(registerRequestDto.getUsername(), registerRequestDto.getEmail(),
                passwordEncoder.encode(registerRequestDto.getPassword()));
        userRepository.save(user);
        return "Register Successfully";
    }
}
