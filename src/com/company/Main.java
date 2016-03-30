package com.company;

import java.io.IOException;

class Main {

    public static void main(String[] args) {
        try {
            new Application().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}