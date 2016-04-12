package com.company;

import com.google.inject.Inject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class OrderStore {

    private PrintWriter printWriter;
    private IOrderTransformer orderTransformer;
    private IOProvider ioprovider;

    @Inject
    OrderStore(IOrderTransformer orderTransformer, IOProvider ioprovider) throws IOException {
        this.ioprovider = ioprovider;
        this.orderTransformer = orderTransformer;
    }


    void saveOrder(Order newOrder) throws IOException {
        printWriter = ioprovider.createWriter();
        try {
            printWriter.println(orderTransformer.toString(newOrder));
        }
        finally {
            printWriter.close();
        }

    }

    public List<Order> getOrders() throws IOException {
        List<Order> orderList = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(ioprovider.createReader());
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            Order order = orderTransformer.parseStringOrder(line);
            orderList.add(order);
        }
        bufferedReader.close();

        return orderList;
    }
}