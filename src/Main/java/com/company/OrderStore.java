package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class OrderStore {

    private final PrintWriter printWriter;
    private OrderParser orderParser;

    OrderStore(PrintWriter printWriter, OrderParser orderParser) {
        this.printWriter = printWriter;
        this.orderParser = orderParser;
    }

    void saveOrder(Order newOrder) {
        printWriter.println(newOrder.getContent());
        printWriter.flush();
    }

    public List<Order> getOrders() throws IOException {
        List<Order> orderList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("orders.txt"));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            Order order = orderParser.parseOrder(line);
            orderList.add(order);
        }

        return orderList;
    }
}