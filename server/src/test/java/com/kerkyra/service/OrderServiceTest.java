package com.kerkyra.service;

import com.kerkyra.model.Order;
import com.kerkyra.repository.OrderRepository;
import com.kerkyra.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.kerkyra.Application;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Andras.Timar on 4/26/2016.
*/
 @ContextConfiguration(classes = Application.class)
 @RunWith(SpringJUnit4ClassRunner.class)
 public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;


    @InjectMocks
    @Autowired
    OrderService orderService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        when(orderRepository.findAll()).thenReturn(orders);
    }

    @Test
    public void getOrders() throws Exception {
        Assert.assertEquals(2, ((List<Order>)orderService.getOrders()).size());
    }
    @Test
    public void insertOrder()
    {
        Order newOrder = new Order();
        orderService.insertOrder(newOrder);
        verify(orderRepository).save(newOrder);
    }
}