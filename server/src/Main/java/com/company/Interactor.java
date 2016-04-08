package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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


    public void newOrderArrived(String order) {
        orderStore.saveOrder(orderParser.parseOrder(order));
    }

    public List<Order> currentOrdersRequested() throws IOException {
        return orderStore.getOrders();
    }

}
