package com.acko.ClaimChain.ClaimChain.controller;

import com.acko.ClaimChain.ClaimChain.models.User;
import com.acko.ClaimChain.ClaimChain.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager; // For handling login authentication manually

    // Registration for User
    @PostMapping("/register")
    public String register(@RequestBody User user, HttpServletRequest request) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER"); // Default role
        userRepository.save(user);
        request.getSession().invalidate(); // Invalidate any existing session

        return "User registered successfully!";
    }

    // Registration for Insurance Company
    @PostMapping("/insurer/register")
    public String registerCompany(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("INSURER"); // Default role
        userRepository.save(user);
        return "Company registered successfully!";
    }

    // Public API - No authentication required
    @GetMapping("/v2/greet")
    public String publicGreet() {
        return "This is a public API.";
    }

    // Secure API - Requires authentication
    @GetMapping("/v1/greet")
    public String secureGreet() {
        return "You are authenticated!";
    }

    // Custom Login API (for REST or manual control) - Optional
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());
        authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return "Login successful!";
    }

    // Logout API - Manual logout if required
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate session and clear authentication context
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "Logged out successfully!";
    }
}
