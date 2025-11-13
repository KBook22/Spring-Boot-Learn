package com.booky.learning.controllers;

import com.booky.learning.dtos.LoginRequestDto;
import com.booky.learning.dtos.LoginResponseDto;
import com.booky.learning.dtos.RegisterRequestDto;
import com.booky.learning.dtos.UserResponseDto;
import com.booky.learning.models.UserModel;
import com.booky.learning.repositories.UserRepository;
import com.booky.learning.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        UserModel user = new UserModel(registerRequestDto.getUsername(), registerRequestDto.getEmail(),
                passwordEncoder.encode(registerRequestDto.getPassword()));
        userRepository.save(user);
        return "Register Successfully";
    }

    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        UserModel user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password e"));
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password p");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponseDto(token, user.getUsername(), user.getEmail(), "Login successfully");
    }

    @GetMapping("/profile")
    public UserResponseDto profile(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email"));
        return new UserResponseDto(user.getId(),user.getUsername(),user.getEmail());
    }
}
