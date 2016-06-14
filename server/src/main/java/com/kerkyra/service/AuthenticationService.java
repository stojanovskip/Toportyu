package com.kerkyra.service;

import com.kerkyra.model.User;
import com.kerkyra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andras.Timar on 6/3/2016.
 */
@Service
public class AuthenticationService implements IAuthenticationService {
    private final SessionManager sessionManager;
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    @Autowired
    public AuthenticationService(SessionManager sessionManager, UserRepository userRepository, PasswordHasher passwordHasher) {
        this.sessionManager = sessionManager;
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public Long login(String username, String password) {

        List<User> usersInRepo = userRepository.findByUsername(username);
        if (usersInRepo.size() > 0) {
            User user = usersInRepo.get(0);
            if (passwordHasher.hash(password).equals(user.getHashedPassword()))
                return sessionManager.setUser(user);
        }
        return null;
    }

    @Override
    public User getUser(Long sessionID) {
        return sessionManager.getUser(sessionID);
    }

    @Override
    public void logout(Long sessionID) {
        sessionManager.removeUser(sessionID);
    }
}
