package com.company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {

            new Application().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}