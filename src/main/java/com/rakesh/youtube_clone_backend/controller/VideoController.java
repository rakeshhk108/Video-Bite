package com.rakesh.youtube_clone_backend.controller;


import com.rakesh.youtube_clone_backend.services.VideoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService vService;

    //usually when we are using post request we are creating a resource in the service side so the response should be CREATED
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void uploadVideo(@RequestBody MultipartFile file)
    {
        vService.uploadVideo(file);
    }
}
