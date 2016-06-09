package com.kerkyra.service;

import com.kerkyra.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Andras.Timar on 6/3/2016.
 */
@Service
@Scope("singleton")
public class SessionManager {

    private final HashMap<Long, User> sessionMap;
    private final Random random;

    public SessionManager() {
        this.sessionMap = new HashMap<Long, User>();
        this.random = new Random();
    }

    public User getUser(Long sessionId) {
        return sessionMap.get(sessionId);
    }

    public Long setUser(User user) {
        Long sessionId = random.nextLong();
        sessionMap.put(sessionId, user);
        return sessionId;
    }

    public void removeUser(Long sessionId) {
        sessionMap.remove(sessionId);
    }
}
