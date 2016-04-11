package com.company;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Madi.Yessirkepov on 4/8/2016.
 */
public class Interactor {
    private final IOrderTransformer orderParser;
    private final OrderStore orderStore;
    private final Gson gson;

    public Interactor(OrderStore orderStore, IOrderTransformer orderParser, Gson gson) throws Exception {
        this.orderParser = orderParser;
        this.orderStore = orderStore;
        this.gson = gson;
    }

    //maybe boolean instead of 2 methods?
    public void newOrderArrived(String order) {
        orderStore.saveOrder(orderParser.fromString(order));
    }

    public void newJsonOrderArrived(String content) {
        orderStore.saveOrder(gson.fromJson(content, Order.class));
    }

    public List<Order> currentOrdersRequested() throws IOException {
        return orderStore.getOrders();
    }

}
