package com.kerkyra.service;

import com.kerkyra.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andras.Timar on 4/25/2016.
 */
@Service
public class OrderService implements IOrderService {
    @Override
    public List<Order> getOrders() {
        return null;
    }
}
