package com.kerkyra.model;

/**
 * Created by Andras.Timar on 6/10/2016.
 */
public class UserDto {
    public final String username;

    public UserDto(User user) {
        if (user != null)
            this.username = user.getUsername();
        else this.username = null;
    }
}
