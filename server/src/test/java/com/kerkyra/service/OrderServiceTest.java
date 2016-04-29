package com.kerkyra.service;

import com.kerkyra.model.Order;
import com.kerkyra.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Andras.Timar on 4/26/2016.
 */
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    private List<Order> orders;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        when(orderRepository.findAll()).thenReturn(orders);
    }

    @Test
    public void getOrders() throws Exception {
        List<Order> ordersBack = (List<Order>) orderService.getOrders();
        Assert.assertEquals(2, ordersBack.size());
        Assert.assertEquals(orders.get(0).getContent(), ordersBack.get(0).getContent());
        Assert.assertEquals(orders.get(1).getContent(), ordersBack.get(1).getContent());
    }

    @Test
    public void insertOrder() {
        Order newOrder = new Order();
        orderService.insertOrder(newOrder);
        verify(orderRepository).save(newOrder);
    }
}