package com.company;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

/**
 * Created by Madi.Yessirkepov on 4/8/2016.
 */
public class Interactor {
    private final OrderParser orderParser;
    private final OrderStore orderStore;

    public Interactor(OrderStore orderStore, OrderParser orderParser) throws Exception {
        this.orderParser = orderParser;
        this.orderStore = orderStore;
    }

    //maybe boolean instead of 2 methods?
    public void newOrderArrived(Order order) {
        orderStore.saveOrder(order);
    }

    public List<Order> currentOrdersRequested() throws IOException {
        return orderStore.getOrders();
    }

}
