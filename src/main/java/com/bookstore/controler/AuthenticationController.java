package com.bookstore.controler;

import com.bookstore.model.CustomUser;
import com.bookstore.service.CustomUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/auth")
public class AuthenticationController {

    @Autowired
    private CustomUserService customerService;

    @PostMapping("/signup")
    public String registerUser(@RequestBody CustomUser customUser){
        customerService.signUp(customUser);
        return "OK";
    }


    @GetMapping("/listUser")
    public List<CustomUser> listUser(){
        return customerService.listUser();
    }

    @PostMapping("/token")
    public String getToken(@RequestParam("userName") String userName, @RequestParam("password") String password){
        String token= customerService.login(userName,password);
        if(StringUtils.isEmpty(token)){
            return "no token found";
        }
        return token;
    }
}