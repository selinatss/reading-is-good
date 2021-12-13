package com.bookstore.service;

import com.bookstore.dao.CustomUserDao;
import com.bookstore.model.CustomUser;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("customUserService")
public class CustomUserDetailsService implements CustomUserService {
    @Autowired
    CustomUserDao userDao;

    @Override
    public String signUp(CustomUser customUser) {
        userDao.save(customUser);
        return StringUtils.EMPTY;
    }

    @Override
    public List<CustomUser>  listUser(){
        return userDao.findAll();
    }


    @Override
    public String login(String userName, String password) {
        CustomUser user = userDao.findByUserNameAndPassword(userName, password);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            userDao.save(user);
            return token;
        }

        return StringUtils.EMPTY;
    }
    @Override
    public Optional findByToken(String token) {
        CustomUser customer= userDao.findByToken(token);
        if(customer != null){
            User user= new User(customer.getUserName(), customer.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return Optional.empty();
    }

}
