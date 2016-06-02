/*package com.kerkyra.web;

import com.kerkyra.model.User;
import com.kerkyra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Petar.Stojanovski on 6/2/2016.
 *//*
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
     userService.addUser(user);
        return user;
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.GET)
    public Iterable<User> getUsers(){
        return userService.getUsers();
    }
}*/