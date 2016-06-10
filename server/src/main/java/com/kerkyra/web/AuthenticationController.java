package com.kerkyra.web;

import com.kerkyra.model.Credentials;
import com.kerkyra.model.User;
import com.kerkyra.model.UserDto;
import com.kerkyra.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Andras.Timar on 6/3/2016.
 */
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/api/users/login", method = RequestMethod.POST)
    public UserDto login(@RequestBody Credentials credentials, HttpServletResponse httpResponse) {
        Long sessionId = authenticationService.login(credentials.getUsername(), credentials.getPassword());
        if (sessionId != null) {
            Cookie cookie = new Cookie("sessionId", sessionId.toString());
            cookie.setPath("/api");
            httpResponse.addCookie(cookie);
            return new UserDto(authenticationService.getUser(sessionId));
        }
        return new UserDto(null);
    }

    @RequestMapping(value = "/api/users/logout", method = RequestMethod.POST)
    public void logout(@CookieValue(value = "sessionId", required = false) Long sessionId, HttpServletResponse httpResponse) {
        if (sessionId != null) {
            authenticationService.logout(sessionId);
            Cookie cookie = new Cookie("sessionId", sessionId.toString());
            cookie.setPath("/api");
            cookie.setMaxAge(0);
            httpResponse.addCookie(cookie);
        }
    }

    @RequestMapping(value = "/api/users/current", method = RequestMethod.GET)
    public UserDto getCurrentUser(@CookieValue(value = "sessionId", required = false) Long sessionId) {
        if (sessionId != null) {
            User user = authenticationService.getUser(sessionId);
            if (user != null) {
                return new UserDto(user);
            }
        }
        return new UserDto(null);
    }

}
