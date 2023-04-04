package com.rakesh.youtube_clone_backend.repository;

import com.rakesh.youtube_clone_backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User , String> {
    Optional<User> findBySub(String sub);
}
