package com.kerkyra.model;

/**
 * Created by Andras.Timar on 6/8/2016.
 */
public class Credentials{

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Credentials(){

    }
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}