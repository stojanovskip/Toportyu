package com.kerkyra.service;

import com.kerkyra.model.Order;

import java.util.List;

/**
 * Created by Andras.Timar on 4/25/2016.
 */
public interface IOrderService {
    Iterable<Order> getOrders();

    void insertOrder(Order order);

    List<Order> getOrdersByTrip(Long tripID);
}
