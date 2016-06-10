package com.kerkyra.web;

import com.kerkyra.model.Order;
import com.kerkyra.model.OrderDto;
import com.kerkyra.service.AuthenticationService;
import com.kerkyra.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Andras.Timar on 4/25/2016.
 */
@RestController
public class OrderController {

    private final OrderService orderService;
    private final AuthenticationService authenticationService;

    @Autowired
    public OrderController(OrderService orderService, AuthenticationService authenticationService) {
        this.orderService = orderService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/api/orders", method = RequestMethod.GET)
    public List<OrderDto> getAllOrders() {
        Iterable<Order> orders = orderService.getOrders();
        if(orders!= null)
        return StreamSupport.stream(orders.spliterator(),false)
                .map(OrderDto::new).collect(Collectors.toList());
        return null;
    }

    @RequestMapping(value = "/api/trips/{id}/orders", method = RequestMethod.GET)
    public List<OrderDto> getOrdersByTrip(@PathVariable Long id) {
        return orderService.getOrdersByTrip(id).stream()
                .map(OrderDto::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/api/orders", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto insertOrder(@CookieValue(value = "sessionId", required = false) Long sessionId, @RequestBody Order order, HttpServletResponse response) {
        if (sessionId != null && authenticationService.getUser(sessionId) != null) {
            order.setUser(authenticationService.getUser(sessionId));
            orderService.insertOrder(order);
            return new OrderDto(order);
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return null;
    }


}
