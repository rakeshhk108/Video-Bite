package com.rakesh.youtube_clone_backend.controller;


import com.rakesh.youtube_clone_backend.dto.CommentDto;
import com.rakesh.youtube_clone_backend.dto.UploadVideoResponse;
import com.rakesh.youtube_clone_backend.dto.VideoDto;
import com.rakesh.youtube_clone_backend.services.VideoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
@Slf4j
public class VideoController {

    private final VideoService vService;

    //usually when we are using post request we are creating a resource in the service side so the response should be CREATED
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponse uploadVideo(@RequestBody MultipartFile file)
    {
        return vService.uploadVideo(file);
    }


    @PostMapping("/upload_thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumnail(@RequestParam("file") MultipartFile file , @RequestParam("videoId") String videoId)
    {

        return vService.uploadThumbnail(file , videoId);
    }


    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto){
        System.out.println(videoDto.toString());
        return vService.editVideo(videoDto);

    }

    @GetMapping("/getVideo/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto getVideoDetails(@PathVariable("videoId")  String videoId )
    {
        log.info("inside the get method");
        return vService.getVideoDetails(videoId);
    }

    @PostMapping("/{videoId}/like")
    @ResponseStatus(HttpStatus.CREATED)
    public VideoDto likeVideo(@PathVariable("videoId") String videoId)
    {
        return vService.setLikeVideo(videoId);

    }
    @PostMapping("/{videoId}/disLike")
    @ResponseStatus(HttpStatus.CREATED)
    public VideoDto disLikeVideo(@PathVariable("videoId") String videoId)
    {
        return vService.setDisLikeVideo(videoId);

    }

    @PostMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@PathVariable String videoId, @RequestBody CommentDto commentDto)
    {
        vService.addComment(videoId,commentDto);
    }

    @GetMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllComments(@PathVariable String videoId){
        return vService.getAllComments(videoId);
    }

    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoDto> getAllVideos(){
        return vService.getAllVideos();
    }





}
