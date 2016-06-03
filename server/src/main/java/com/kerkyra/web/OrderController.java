package com.kerkyra.web;

import com.kerkyra.model.Order;
import com.kerkyra.service.AuthenticationService;
import com.kerkyra.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Andras.Timar on 4/25/2016.
 */
@RestController
public class OrderController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Iterable<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @RequestMapping(value = "/trips/{id}/orders", method = RequestMethod.GET)
    public List<Order> getOrdersByTrip(@CookieValue("sessionID") long sessionID, @PathVariable Long id)
    {
        return orderService.getOrdersByTrip(id);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> insertOrder(@CookieValue("sessionID") long sessionID, @RequestBody Order order) {
       if(authenticationService.getUser(sessionID)!=null){
        orderService.insertOrder(order);
        return new ResponseEntity<Object>(order, HttpStatus.OK);}
        return new ResponseEntity<Object>(null,HttpStatus.UNAUTHORIZED);
    }


}
