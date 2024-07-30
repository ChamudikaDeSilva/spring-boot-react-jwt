package com.example.springbootreactjwt.controller;

import com.example.springbootreactjwt.model.User;
import com.example.springbootreactjwt.security.JwtTokenUtil;
import com.example.springbootreactjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            var authentication = authenticationManager.authenticate(authenticationToken);
            var jwt = jwtTokenUtil.generateToken(authentication.getName());
            return jwt;
        } catch (AuthenticationException e) {
            return "Invalid credentials";
        }
    }
}