package com.kerkyra.service;

import com.kerkyra.model.User;

/**
 * Created by Andras.Timar on 6/13/2016.
 */
public interface IAuthenticationService {
    Long login(String username, String password);

    User getUser(Long sessionID);

    void logout(Long sessionID);
}
