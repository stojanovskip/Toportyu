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

    private final HashMap<Long,User> sessionMap;
    private final Random random;

    public SessionManager() {
        this.sessionMap = new HashMap<Long,User>();
        this.random = new Random();
    }

    public User getUser(long sessionID){
        return null;
    }
    public long setUser(User user){
        return 0;
    }
    public void removeUser(long sessionID){

    }
}
