package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class OrderStore {

    private final PrintWriter printWriter;
    private IOrderTransformer orderTransformer;
    OrderStore(String path, OrderTransformer orderTransformer) throws IOException {
        this.printWriter = new PrintWriter(new FileWriter(path),true);
        this.orderTransformer = orderTransformer;
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
            Order order = orderTransformer.parseStringOrder(line);
            orderList.add(order);
        }

        return orderList;
    }
}