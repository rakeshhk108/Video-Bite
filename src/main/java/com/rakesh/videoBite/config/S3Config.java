package com.rakesh.videoBite.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//denotes that the following class is a configuration class
@Configuration
public class S3Config {

    //@Value use to inject properties for the application.properties file
    //So we can add the aws credential in  the application.properties or you can add it as an environment variable
    //So we need an acessKey, secretKey and region
    @Value("${cloud.aws.credential.access-key}")
    private String acessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secret;
    @Value("${cloud.aws.region.static}")
    private String region;


    //@Bean is a method level annotation
    //we tell the spring to create and manage the livecycle of the AmazonS3 instance
    @Bean
    public AmazonS3 s3(){

        //creating a instance of AWS credential and provide the access key and secrete key
        AWSCredentials awsCredentials = new BasicAWSCredentials(acessKey , secret);

        //creating an instance of AmazonS3ClienBuilde with the specified region and passing the credentials and returning it

        return AmazonS3ClientBuilder.standard()
                .withRegion("ap-south-1")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

}
