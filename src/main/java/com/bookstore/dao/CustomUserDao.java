package com.bookstore.dao;

import com.bookstore.model.CustomUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomUserDao extends MongoRepository<CustomUser, String> {
    CustomUser findByUserNameAndPassword(String userName, String password);
    CustomUser findByToken(String email);
}
