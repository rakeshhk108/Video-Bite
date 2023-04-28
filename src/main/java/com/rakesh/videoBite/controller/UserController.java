package com.rakesh.videoBite.controller;


import com.rakesh.videoBite.dto.VideoDto;
import com.rakesh.videoBite.services.UserRegistrationService;
import com.rakesh.videoBite.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //method to regester the user it will take the authentication object and
    public String register(Authentication auth){

        //extrating jwt from the authentication object
        Jwt jwt = (Jwt) auth.getPrincipal();

        //passing it to userRegistration Service
        return userRegistrationService.registerUser(jwt.getTokenValue());

    }

    //this method is to subscribe to the user
    @PostMapping("/{userId}/subscribe")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean subscribeUser(@PathVariable("userId") String userId)
    {
      userService.subscribeUser(userId);
      return true;
    }

    //this method is to unsubscribe the user
    @PostMapping("/{userId}/unSubscribe")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean unSubscribeUser(@PathVariable("userId") String userId)
    {
        userService.unSubscribeUser(userId);
        return true;
    }


    //fetch the watched history
    @GetMapping("/{userId}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoDto> userHistory(@PathVariable String userId)
    {
        return userService.userHistory(userId);
    }

    //fetch like videos list
    @GetMapping("/{userId}/likeVideos")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoDto> LikedVideos(@PathVariable String userId)
    {
        return userService.likedVideos(userId);
    }


}
