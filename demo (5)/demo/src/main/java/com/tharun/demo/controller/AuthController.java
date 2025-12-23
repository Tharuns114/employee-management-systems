package com.tharun.demo.controller;

import com.tharun.demo.model.User;
import com.tharun.demo.repository.UserRepository;
import com.tharun.demo.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        User dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(dbUser.getUsername());
        return Map.of("token", token);
    }
}
