package com.rakesh.youtube_clone_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
    @Indexed(unique = true)
    private String sub;
    private Set<String> subscribedToUser = ConcurrentHashMap.newKeySet();
    private Set<String> subscribers = ConcurrentHashMap.newKeySet();
    private Set<String> videoHistory = ConcurrentHashMap.newKeySet();
    private Set<String> likedVideos  = ConcurrentHashMap.newKeySet();
    private Set<String> disLikedVideos = ConcurrentHashMap.newKeySet();


    public void addToLikeVideos(String videoId){
        likedVideos.add(videoId);
    }
    public void removeFromLikeVideos(String videoId) {
        likedVideos.remove(videoId);
    }

    public void addToDisLikeVideos(String videoId){
        disLikedVideos.add(videoId);
    }
    public void removeFromDisLikeVideos(String videoId){
        disLikedVideos.remove(videoId);
    }


    public void addToVideoHistory(String videoId) {
        videoHistory.add(videoId);
    }

    public void addToSubscribesSet(String userId) {
        subscribers.add(userId);
    }

    public void addToSubscribedToUserSet(String userId) {
        subscribedToUser.add(userId);
    }

    public void removeToSubscribedToUserSet(String userId) {
        subscribedToUser.remove(userId);
    }

    public void removeToSubscribesSet(String userId) {
        subscribers.remove(userId);
    }


}


