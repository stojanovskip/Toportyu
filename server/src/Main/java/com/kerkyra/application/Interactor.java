package com.kerkyra.application;

import com.google.inject.Inject;
import com.kerkyra.datahandling.IOrderStore;
import com.kerkyra.datahandling.Order;

import java.io.IOException;
import java.util.List;

/**
 * Created by Madi.Yessirkepov on 4/8/2016.
 */
public class Interactor {
    private final IOrderStore orderStore;

    @Inject
    public Interactor(IOrderStore orderStore) throws Exception {
        this.orderStore = orderStore;
    }

    public void newOrderArrived(Order order) {
        try {
            orderStore.saveOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Order> currentOrdersRequested() throws IOException {
        return orderStore.getOrders();
    }

}
