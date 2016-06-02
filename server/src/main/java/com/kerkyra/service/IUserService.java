package com.kerkyra.service;

import com.kerkyra.model.User;

/**
 * Created by Petar.Stojanovski on 6/2/2016.
 */
public interface IUserService {
    void addUser(User user);
    Iterable<User> getUsers();
}
