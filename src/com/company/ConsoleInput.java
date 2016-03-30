package com.company;
import java.io.*;

public class ConsoleInput {

    BufferedReader br;

    public ConsoleInput()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    public String ReadInput()
    {
            String s = "";

                System.out.println("Input new order: ");
                try {
                    s = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return s;
    }
    public String BoolInput()
    {
        String s = "";

        System.out.println("Would you like to order more?(y/n)");
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
