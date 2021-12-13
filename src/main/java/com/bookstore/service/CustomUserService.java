package com.bookstore.service;

import com.bookstore.model.CustomUser;

import java.util.List;
import java.util.Optional;

public interface CustomUserService {
    String signUp(CustomUser customUser);
    String login(String userName, String password);
    List<CustomUser> listUser();
    Optional<CustomUser> findByToken(String token);
}
