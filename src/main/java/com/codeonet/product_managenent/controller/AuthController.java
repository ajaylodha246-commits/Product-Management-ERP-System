package com.codeonet.product_managenent.controller;

import com.codeonet.product_managenent.dto.AuthResponse;
import com.codeonet.product_managenent.entity.User;
import com.codeonet.product_managenent.repository.UserRepository;
import com.codeonet.product_managenent.security.JwtUtil;
import com.codeonet.product_managenent.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsServiceImpl service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public String login(@RequestBody User user) {

        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        UserDetails userDetails = service.loadUserByUsername(user.getUsername());
        return jwtUtil.generateToken(userDetails);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user, Authentication authentication) {

        if (authentication == null
                || authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new RuntimeException("Only admin can register users");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        } else {
            user.setRole(user.getRole().trim().toUpperCase());
        }
        return userRepository.save(user);
    }

    @GetMapping("/me")
    public AuthResponse me(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new AuthResponse(null, user.getUsername(), user.getRole());
    }
}
