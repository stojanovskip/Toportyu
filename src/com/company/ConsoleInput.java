package com.company; /**
 * Created by Andras.Timar on 3/21/2016.
 */

import java.io.*;

public class ConsoleInput {

    BufferedReader br;

    public ConsoleInput() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String ReadInput() {

        String s = "";
        System.out.println("Input new order: ");
        try {
            s = br.readLine() + System.getProperty("line.separator");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }
}
