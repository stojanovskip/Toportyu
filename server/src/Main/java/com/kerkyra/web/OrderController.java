package com.kerkyra.web;

import com.kerkyra.model.Order;
import com.kerkyra.model.Trip;
import com.kerkyra.service.OrderService;
import com.kerkyra.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Andras.Timar on 4/25/2016.
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    TripService tripService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Iterable<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertOrder(@RequestBody Order order) {
        orderService.insertOrder(order);
    }

    @RequestMapping(value ="/trips", method=RequestMethod.POST)
    public void insertTrip(@RequestBody Trip t)
    {
        tripService.insertTrip(t);
    }
    @RequestMapping(value ="/trips", method=RequestMethod.GET)
    public Iterable<Trip> getTrips()
    {
        return tripService.getTrips();
    }
}
