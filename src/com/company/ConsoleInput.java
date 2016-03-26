package com.company;

import java.io.*;

public class ConsoleInput {
    BufferedReader br;
    Listener listener;

    public ConsoleInput(Listener listener) {
        this.listener = listener;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String ReadInput() throws Exception {
        System.out.println("Input new order: ");
        String s = br.readLine();

        if (s.equals(":q")) listener.userWantsToQuit();
        else listener.newOrderArrived(s);

        return s;
    }
}
