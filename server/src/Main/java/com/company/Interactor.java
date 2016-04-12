package com.company;

import java.io.IOException;
import java.util.List;

/**
 * Created by Madi.Yessirkepov on 4/8/2016.
 */
class Interactor {
    private final OrderStore orderStore;

    public Interactor(OrderStore orderStore) throws Exception {
        this.orderStore = orderStore;
    }

    public void newOrderArrived(Order order) {
        orderStore.saveOrder(order);
    }

    public List<Order> currentOrdersRequested() throws IOException {
        return orderStore.getOrders();
    }

}
