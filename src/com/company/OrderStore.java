package com.company;

import java.util.*;
import java.io.*;

public class OrderStore {

    Order order = new Order();
    private List<String> OrderList = new ArrayList<String>();

    public void addOrder(Order newOrder) {
        FileWriter write = null;
        try {
            write = new FileWriter("orders.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter print_line = new PrintWriter(write);
        print_line.printf("%s" + "%n" + "%n", newOrder.getContent());
        print_line.close();

    }

}
