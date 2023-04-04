package com.rakesh.youtube_clone_backend.services;

import com.rakesh.youtube_clone_backend.model.User;
import com.rakesh.youtube_clone_backend.model.Video;
import com.rakesh.youtube_clone_backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

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



}
