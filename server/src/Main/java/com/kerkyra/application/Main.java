package com.kerkyra.application;

import com.kerkyra.application.Application;

public class Main {
    public static void main(String[] args) {
        try {
            new Application().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}