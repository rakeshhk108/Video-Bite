package com.rakesh.youtube_clone_backend.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${cloud.aws.credential.access-key}")
    private String acessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secret;

    @Value("${cloud.aws.region.static}")
    private String region;



    @Bean
    public AmazonS3 s3(){

        AWSCredentials awsCredentials = new BasicAWSCredentials(acessKey , secret);

        return AmazonS3ClientBuilder.standard()
                .withRegion("ap-south-1")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

}
