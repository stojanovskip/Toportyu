package com.kerkyra;

import com.kerkyra.model.User;
import com.kerkyra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * Created by Andras.Timar on 5/30/2016.
 */
@Component
@Scope("singleton")
public class SessionManager {

    HashMap<Long,User> sessionMap;
    Random random;

    public SessionManager() {
        this.sessionMap = new HashMap<Long,User>();
        this.random = new Random();
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<User>();
        users.addAll(sessionMap.values());

        return users;
    }
    public Long addUser(User user) {
        Long sessionID = random.nextLong();
        sessionMap.put(sessionID,user);
        return sessionID;
    }
    public User getUser(Long sessionID) {
        return sessionMap.get(sessionID);
    }
    public void removeUser(Long sessionID){
        sessionMap.remove(sessionID);
    }
}
