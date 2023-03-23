package com.rakesh.youtube_clone_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document(value = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailAddress;
    private Set<String> subscribedToUser;
    private Set<String> subscribers;
    private List<String> videoHistory;
    private Set<String> likedVideos;
    private Set<String> disLikedVideos;

}


