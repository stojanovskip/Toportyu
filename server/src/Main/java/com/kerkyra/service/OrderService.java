package com.kerkyra.service;

import com.kerkyra.model.Order;
import com.kerkyra.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andras.Timar on 4/25/2016.
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void insertOrder(Order order) {
        orderRepository.save(order);
    }
}
