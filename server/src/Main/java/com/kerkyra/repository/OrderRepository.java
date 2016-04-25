package com.kerkyra.repository;

import com.kerkyra.model.Order;

import java.util.List;

/**
 * Created by Andras.Timar on 4/25/2016.
 */
public interface OrderRepository extends org.springframework.data.repository.Repository<Order, Long> {

    public List<Order> findAll();
}
