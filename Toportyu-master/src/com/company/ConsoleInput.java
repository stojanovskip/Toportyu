package com.company;

import java.io.*;

class ConsoleInput {
    private final BufferedReader br;
    private final Listener listener;

    ConsoleInput(Listener listener) {
        this.listener = listener;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    void ReadInput() throws IOException {
        System.out.println("Input new order: ");
        String s = br.readLine();

        if (s.equals(":q")) listener.onQuitRequest();
        else listener.newOrderArrived(s);

    }
}
