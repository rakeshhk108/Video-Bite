package com.rakesh.youtube_clone_backend.services;

import com.rakesh.youtube_clone_backend.model.User;
import com.rakesh.youtube_clone_backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;



    //get the credentials of the current user
    public User getCurrentUser(){
       String sub =  ((Jwt)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getClaim("sub");
       return userRepo.findBySub(sub).orElseThrow(()-> new IllegalArgumentException("cannot find user with the sub - " + sub) );
    }


    //add the liked video in the set of the liked by the current user
    public void addToLikedVideos(String videoId){
        User currentUser = getCurrentUser();
        currentUser.addToLikeVideos(videoId);
        userRepo.save(currentUser);
    }

    public void addToDisLikedVideos(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.addToDisLikeVideos(videoId);
        userRepo.save(currentUser);
    }

    public void removeFormLikeVideo(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.removeFromLikeVideos(videoId);
        userRepo.save(currentUser);
    }

    public void removeFromDislikeVideos(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.removeFromDisLikeVideos(videoId);
        userRepo.save(currentUser);
    }


    //checking if the current user has liked video and is it there in set of liked video
    public boolean ifLikedVideos(String videoId){
        User currentUser = getCurrentUser();
       return currentUser.getLikedVideos().stream()
                .anyMatch(likedVideoId ->  likedVideoId.equals(videoId));
    }

    //checking if the current user has disliked the video by checking in the set of dislike videos in the user entity
    public boolean ifDisLikedVideos(String videoId){
        User currentUser = getCurrentUser();
        return currentUser.getDisLikedVideos().stream()
                .anyMatch(likedVideoId ->  likedVideoId.equals(videoId));
    }

    public void addVideoToHistory(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.addToVideoHistory(videoId);
        userRepo.save(currentUser);
    }


    public void subscribeUser(String userId) {
        //Retrieve the current user and add the userId to the subscribed to users set
        User currentUser = getCurrentUser();
        currentUser.addToSubscribedToUserSet(userId);
        //Retrieve the target user and add the current user to subscribers list
        User targetUser = getUserById(userId);
        targetUser.addToSubscribesSet(currentUser.getId());
    }

    public void unSubscribeUser(String userId) {
        //Retrieve the current user and remove the userId to the subscribed to users set
        User currentUser = getCurrentUser();
        currentUser.removeToSubscribedToUserSet(userId);
        //Retrieve the target user and remove the current user to subscribers list
        User targetUser = getUserById(userId);
        targetUser.removeToSubscribesSet(currentUser.getId());
    }


    public Set<String> userHistory(String userId) {
        User targetUser = getUserById(userId);
        return targetUser.getVideoHistory();
    }

    private User getUserById(String userId) {
        return userRepo.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("no user found with the userId: " + userId));
    }
}
