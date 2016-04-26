package com.kerkyra.service;

import com.kerkyra.model.Order;

/**
 * Created by Andras.Timar on 4/25/2016.
 */
public interface IOrderService {
    public Iterable<Order> getOrders();

    void insertOrder(Order order);
}
