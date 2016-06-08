package com.kerkyra.web;

import org.springframework.web.bind.annotation.*;

/**
 * Created by Andras.Timar on 6/3/2016.
 */
@RestController
public class AuthenticationController {

    @RequestMapping(value = "/api/users/login", method = RequestMethod.POST)
    public UserResponse login(@RequestBody String username, @RequestBody String password) {
        return null;
    }

    @RequestMapping(value = "/api/users/logout", method = RequestMethod.POST)
    public void logout(@CookieValue("sessionID") long sessionID) {
    }

    @RequestMapping(value = "/api/users/current", method = RequestMethod.GET)
    public UserResponse getCurrentUser(@CookieValue("sessionID") long sessionID) {
        return null;
    }

    public static class UserResponse {
        public String username;
    }
}
