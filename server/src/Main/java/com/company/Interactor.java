package com.company;

import com.google.inject.Inject;

import java.io.IOException;
import java.util.List;

/**
 * Created by Madi.Yessirkepov on 4/8/2016.
 */
class Interactor {
    private final OrderStoreFile orderStoreFile;
    @Inject
    public Interactor(OrderStoreFile orderStoreFile) throws Exception {
        this.orderStoreFile = orderStoreFile;
    }

    public void newOrderArrived(Order order) {
        try {
            orderStoreFile.saveOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Order> currentOrdersRequested() throws IOException {
        return orderStoreFile.getOrders();
    }

}
