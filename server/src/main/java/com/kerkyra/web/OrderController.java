package com.kerkyra.web;

import com.kerkyra.model.Order;
import com.kerkyra.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Andras.Timar on 4/25/2016.
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/api/orders", method = RequestMethod.GET)
    public Iterable<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @RequestMapping(value = "/api/trips/{id}/orders", method = RequestMethod.GET)
    public List<Order> getOrdersByTrip(@PathVariable Long id) {
        return orderService.getOrdersByTrip(id);
    }

    @RequestMapping(value = "/api/orders", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order insertOrder(@RequestBody Order order) {
        orderService.insertOrder(order);
        return order;
    }


}
