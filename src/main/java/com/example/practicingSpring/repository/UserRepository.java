package com.example.practicingSpring.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.practicingSpring.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByName(String name);
    User findByUsername(String username);
}

