package com.rakesh.youtube_clone_backend.controller;


import com.rakesh.youtube_clone_backend.services.UserRegistrationService;
import com.rakesh.youtube_clone_backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRegistrationService userRegistrationService;
    private final UserService userService;

    @CrossOrigin
    @GetMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public String register(Authentication auth){
        Jwt jwt = (Jwt) auth.getPrincipal();

        return userRegistrationService.registerUser(jwt.getTokenValue());

    }

    @PostMapping("/{userId}/subscribe")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean subscribeUser(@PathVariable("userId") String userId)
    {
      userService.subscribeUser(userId);
      return true;
    }

    @PostMapping("/{userId}/unSubscribe")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean unSubscribeUser(@PathVariable("userId") String userId)
    {
        userService.unSubscribeUser(userId);
        return true;
    }


    @GetMapping("/{userId}/history")
    @ResponseStatus(HttpStatus.OK)
    public Set<String> userHistory(@PathVariable String userId)
    {
        return userService.userHistory(userId);
    }
}
