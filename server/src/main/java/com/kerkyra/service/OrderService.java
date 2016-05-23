package com.kerkyra.service;

import com.kerkyra.model.Order;
import com.kerkyra.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andras.Timar on 4/25/2016.
 */
@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService (OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void insertOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByTrip(Long tripID) {
        return orderRepository.findByTrip_id(tripID);
    }
}
