package com.rakesh.youtube_clone_backend;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class YoutubeCloneBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoutubeCloneBackendApplication.class, args);
    }


}
