package com.example.springbootreactjwt.service;

import com.example.springbootreactjwt.model.User;

public interface UserService {

    User findByUsername(String username);
    User save(User user);
} 
