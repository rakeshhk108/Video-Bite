package com.rakesh.videoBite.repository;


import com.rakesh.videoBite.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {
}
