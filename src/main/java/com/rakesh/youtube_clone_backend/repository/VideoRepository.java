package com.rakesh.youtube_clone_backend.repository;


import com.rakesh.youtube_clone_backend.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {
}
