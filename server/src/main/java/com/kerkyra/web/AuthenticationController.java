package com.kerkyra.web;

import com.kerkyra.model.Order;
import com.kerkyra.model.User;
import com.kerkyra.service.AuthenticationService;
import com.kerkyra.service.OrderService;
import com.mysql.fabric.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by andras.timar on 6/2/2016.
 */
@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response) {
        long sessionID = authenticationService.login(user);
        if (sessionID != -1) {
            response.setHeader("set-cookie", "sessionID=" + sessionID + ";path=/;");
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return authenticationService.getUsers();
    }

    @RequestMapping(value = "/users/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(@CookieValue("sessionID") long sessionID, HttpServletResponse response) {
        response.setHeader("set-ccokie", "sessionID=-1;expires=Thu, 18 Dec 2013 12:00:00 UTC;path=/");
        authenticationService.logOut(sessionID);
        return new ResponseEntity<Object>(HttpStatus.OK);


    }

    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody User newUser) {
        authenticationService.register(newUser);
        return new ResponseEntity<Object>(newUser, HttpStatus.OK);
    }


}
