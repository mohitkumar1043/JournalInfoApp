package com.mohit.journalApp.repo;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.mohit.journalApp.entity.User;

public interface UserRepo extends MongoRepository<User, ObjectId>{
Optional<User> findByUsername(String username);
}
