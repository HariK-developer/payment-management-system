package com.smartpay.controller;

import com.smartpay.dto.ApiResponse;
import com.smartpay.entity.User;
import com.smartpay.repository.UserRepository;
import com.smartpay.security.JwtUtil;
import com.smartpay.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth") // ✅ added leading slash
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepo, JwtUtil jwtUtil, UserService userService) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");

        User saved = userService.create(user);
        return ApiResponse.success("User registered successfully", saved);
    }


    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody User loginRequest) {
        Optional<User> userOpt = userRepo.findByEmail(loginRequest.getEmail());

        if (userOpt.isEmpty() || !encoder.matches(loginRequest.getPassword(), userOpt.get().getPassword())) {
            return ApiResponse.failure("Invalid email or password", null);
        }

        String token = jwtUtil.generateToken(loginRequest.getEmail());
        return ApiResponse.success("Login successful", token);
    }
}
