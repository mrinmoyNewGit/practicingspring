package com.example.practicingSpring.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.example.practicingSpring.model.User;
@Service
public interface UserRepository extends MongoRepository<User, String> {
    User findByName(String name);
    User findByEmail(String email);
}

