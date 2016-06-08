package com.kerkyra.service;

import com.kerkyra.model.User;
import com.kerkyra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andras.Timar on 6/3/2016.
 */
@Service
public class AuthenticationService {
    private final SessionManager sessionManager;
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    @Autowired
    public AuthenticationService(SessionManager sessionManager, UserRepository userRepository, PasswordHasher passwordHasher) {
        this.sessionManager = sessionManager;
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public long login(String username, String password) {
        return 0;
    }

    public User getUser(long sessionID) {
        return null;
    }

    public void logout(long sessionID) {
    }
}
