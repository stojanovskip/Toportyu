package com.kerkyra.service;

import com.kerkyra.PasswordHasher;
import com.kerkyra.SessionManager;
import com.kerkyra.model.User;
import com.kerkyra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andras.timar on 6/2/2016.
 */
@Service
public class AuthenticationService {

    SessionManager sessionManager;
    UserRepository userRepository;

    PasswordHasher hasher;

    @Autowired
    public AuthenticationService(SessionManager sessionManager, UserRepository userRepository,PasswordHasher hasher) {
        this.userRepository = userRepository;
        this.sessionManager = sessionManager;
        this.hasher = hasher;
    }

    public long login(User u) {
       List<User> result = userRepository.findByName(u.getName());
        if(result==null) return -1;
        if(result.size()==1) {
            String hashedpass = hasher.hash(u.getPassword());
            if(result.get(0).getPassword().equals(hashedpass)){
                u.setPassword(hashedpass);
                return sessionManager.addUser(u);
            }
        }
        return -1;
    }

    public boolean logOut(Long sessionID) {
        try {
            sessionManager.removeUser(sessionID);
            return true;

        }catch(Exception ex){
            return false;
        }
    }

    public List<User> getUsers() {
        return sessionManager.getUsers();
    }

    public User getUser(Long sessionId){
        return sessionManager.getUser(sessionId);
    }

    public boolean register(User newUser){
        List<User> result = userRepository.findByName(newUser.getName());
        if(result.size()==0) {
            newUser.setPassword(hasher.hash(newUser.getPassword()));
            userRepository.save(newUser);
        }
        return result.size()==0;
    }
}
