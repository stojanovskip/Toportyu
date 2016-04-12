package com.company;

import com.google.inject.Inject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class OrderStore {

    private final PrintWriter printWriter;
    private IOrderTransformer orderTransformer;
    private IOProvider ioprovider;
    @Inject
    OrderStore(IOrderTransformer orderTransformer, IOProvider ioprovider) throws IOException {
        this.ioprovider = ioprovider;
        this.printWriter = ioprovider.createWriter();
        this.orderTransformer = orderTransformer;
    }


    void saveOrder(Order newOrder) {
        printWriter.println(orderTransformer.toString(newOrder));
        printWriter.flush();
    }

    public List<Order> getOrders() throws IOException {
        List<Order> orderList = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(ioprovider.createReader());
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            Order order = orderTransformer.parseStringOrder(line);
            orderList.add(order);
        }


        return orderList;
    }
}