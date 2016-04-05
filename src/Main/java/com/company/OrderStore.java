package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class OrderStore {

    private final PrintWriter printWriter;

    OrderStore(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    void saveOrder(Order newOrder) {
        printWriter.println(newOrder.getContent());
        printWriter.flush();
    }
    public List<Order> getOrders() throws IOException {
        OrderParser orderparser = new OrderParser();
        List<Order> orderList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("orders.txt"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Order order = orderparser.parseOrder(line);
            orderList.add(order);
        }

        return orderList;
    }
}