package com.rakesh.youtube_clone_backend.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakesh.youtube_clone_backend.dto.UserInfo;
import com.rakesh.youtube_clone_backend.model.User;
import com.rakesh.youtube_clone_backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

   @Value("${auth0.userinfoEndpoint}")
   private String userInfoEndpoint;
    private final UserRepo userRepo;

    public void registerUser(String token){
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(userInfoEndpoint))
                .setHeader("Authorization", String.format("Bearer %s", token))
                .build();

       HttpClient httpClient =  HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

       try{
           //first argument is send http request and the second is we want the response in the string format
           HttpResponse<String> responseString  = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
           String body = responseString.body();

           ObjectMapper objectMapper = new ObjectMapper();
           objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES ,false);
           UserInfo userInfo = objectMapper.readValue(body , UserInfo.class);

           User user = new User();

           user.setFirstName(userInfo.getGiveName());
           user.setLastName(userInfo.getFamilyName());
           user.setFullName(userInfo.getName());
           user.setEmailAddress(userInfo.getEmail());
           user.setSub(userInfo.getSub());

           userRepo.save(user);

       }
       catch (Exception exception)
       {
           throw new RuntimeException("Exception occured while registering user" , exception);
       }



    }
}
