package com.kerkyra.service;

import com.kerkyra.model.User;

import java.util.List;

/**
 * Created by Andras.Timar on 6/3/2016.
 */
public interface IAuthenticationService {
    long login(User u);

    boolean logOut(Long sessionID);

    List<User> getUsers();

    User getUser(Long sessionId);

    boolean register(User newUser);
}
