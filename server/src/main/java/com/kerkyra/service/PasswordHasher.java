package com.kerkyra.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Andras.Timar on 6/3/2016.
 */
@Service
public class PasswordHasher {
    public String hash(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            String salt = "kerkyra_project_5463453453453436236254";
            String passWithSalt = password + salt;
            byte[] passBytes = passWithSalt.getBytes();
            byte[] passHash = sha256.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < passHash.length; i++) {
                sb.append(Integer.toString((passHash[i] & 0xff) + 0x100, 16).substring(1));
            }
            String generatedPassword = sb.toString();
            return generatedPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
