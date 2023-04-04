package com.rakesh.youtube_clone_backend.controller;


import com.rakesh.youtube_clone_backend.services.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRegistrationService userService;

    @GetMapping("/register")
    public String register(Authentication auth){
        Jwt jwt = (Jwt) auth.getPrincipal();

        userService.registerUser(jwt.getTokenValue());
        return "User Registration Successful";
    }
}
