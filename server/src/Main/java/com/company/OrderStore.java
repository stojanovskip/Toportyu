package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class OrderStore {

    private final PrintWriter printWriter;
    private IOrderTransformer orderTransformer;

    OrderStore(PrintWriter printWriter, IOrderTransformer orderParser) {
        this.printWriter = printWriter;
        this.orderTransformer = orderParser;
    }

    void saveOrder(Order newOrder) {
        printWriter.println(orderTransformer.toString(newOrder));
        printWriter.flush();
    }

	public List<Order> getOrders() throws IOException {
        List<Order> orderList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("orders.txt"));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            Order order = orderTransformer.fromString(line);
            orderList.add(order);
        }

        return orderList;
    }
}