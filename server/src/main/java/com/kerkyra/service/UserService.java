package com.kerkyra.service;

import com.kerkyra.model.User;
import com.kerkyra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Petar.Stojanovski on 6/2/2016.
 */
public class UserService implements IUserService{

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
}
