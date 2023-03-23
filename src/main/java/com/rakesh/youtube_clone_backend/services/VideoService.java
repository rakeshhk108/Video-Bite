package com.rakesh.youtube_clone_backend.services;


import com.rakesh.youtube_clone_backend.model.Video;
import com.rakesh.youtube_clone_backend.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;


    public void uploadVideo(MultipartFile multipartFile){

        //use aws s3 service to save the vedio
        String url = s3Service.uploadfile(multipartFile);

        Video video = new Video();
        video.setVideoUrl(url);
        // save the data of the video in database
        videoRepository.save(video);
    }
}
