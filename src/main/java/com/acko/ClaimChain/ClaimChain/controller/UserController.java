package com.acko.ClaimChain.ClaimChain.controller;

import com.acko.ClaimChain.ClaimChain.dto.LoginRequest;
import com.acko.ClaimChain.ClaimChain.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  /*  @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody LoginRequest loginRequest) {
        userService.saveUserCredentials(loginRequest.getUsername(), loginRequest.getPassword());
        // Save roles as needed
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        //Subject subject = SecurityUtils.getSubject();
        //UsernamePasswordToken token = new UsernamePasswordToken(loginRequest.getUsername(), loginRequest.getPassword());
        try {
          //  subject.login(token);
            return "Login successful";
        } catch (Exception e) {
            return "Login failed: " + e.getMessage();
        }
    }*/
}