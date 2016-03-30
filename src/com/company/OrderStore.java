package com.company;
import java.util.*;
import java.io.*;
public class OrderStore {

    Order order = new Order();

    public void addOrder(String newOrder)
    {
        order.setContent(newOrder);
        FileWriter write = null;
        try {
            write = new FileWriter("orders.txt",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter print_line = new PrintWriter(write);
        print_line.printf("%s"+"%n"+"%n",order.getContent());
        print_line.close();
    }
}
