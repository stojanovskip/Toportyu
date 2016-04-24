package com.kerkyra.application;

public class Main {
    public static void main(String[] args) {
        try {
            new Application().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}