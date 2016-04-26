package com.kerkyra.web;

import com.kerkyra.model.Order;
import com.kerkyra.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andras.Timar on 4/25/2016.
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Iterable<Order> getAllOrders(){
    return orderService.getOrders();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertOrder(@RequestBody Order order)
    {
        orderService.insertOrder(order);
    }
}
