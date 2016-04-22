package com.kerkyra.datahandling;

import com.google.inject.Inject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderStoreFile implements IOrderStore {

    private PrintWriter printWriter;
    private IOrderTransformer orderTransformer;
    private IOProvider ioprovider;
    final Lock lock = new ReentrantLock();

    @Inject
    public OrderStoreFile(IOrderTransformer orderTransformer, IOProvider ioprovider) throws IOException {
        this.ioprovider = ioprovider;
        this.orderTransformer = orderTransformer;
    }

    @Override
    public void saveOrder(Order newOrder) throws IOException {
        printWriter = ioprovider.createWriter();
        try {
            lock.lock();
            printWriter.println(orderTransformer.toJson(newOrder));
        } finally {
            printWriter.close();
            lock.unlock();
        }
    }

    @Override
    public List<Order> getOrders() throws IOException {
        List<Order> orderList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(ioprovider.createReader());
        String line;
        try {
            lock.lock();
            while ((line = bufferedReader.readLine()) != null) {
                Order order = orderTransformer.parseJsonOrder(line);
                orderList.add(order);
            }
        } finally {
            bufferedReader.close();
            lock.unlock();
        }
        return orderList;
    }

}