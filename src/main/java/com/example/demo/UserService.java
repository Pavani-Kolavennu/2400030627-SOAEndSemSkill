package com.example.demo;


import org.springframework.stereotype.Service;

@Service
public class UserService {

  
    private final JwtUtil jwtUtil;

    public UserService(JwtUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }


    public String login(String username, String role, String password) {

      

        return jwtUtil.generateToken(username,role);
    }
}