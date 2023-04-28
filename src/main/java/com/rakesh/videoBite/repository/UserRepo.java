package com.rakesh.videoBite.repository;

import com.rakesh.videoBite.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User , String> {
    Optional<User> findBySub(String sub);
}
