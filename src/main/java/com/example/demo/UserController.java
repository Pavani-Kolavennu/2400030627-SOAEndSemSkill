package com.example.demo;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    

    @PostMapping("/jwt/generate")
    public String login(@RequestBody User user) {

        return userService.login( user.getUsername(), user.getRole(),user.getPassword()
        );
    }
}