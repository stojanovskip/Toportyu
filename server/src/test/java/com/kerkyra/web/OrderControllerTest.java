package com.kerkyra.web;

import com.kerkyra.model.Order;
import com.kerkyra.model.OrderDto;
import com.kerkyra.model.Trip;
import com.kerkyra.model.User;
import com.kerkyra.service.AuthenticationService;
import com.kerkyra.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Andras.Timar on 6/10/2016.
 */
public class OrderControllerTest {
    @Mock
    AuthenticationService authenticationService;
    @Mock
    OrderService orderService;

    OrderController orderController;

    User user;
    Order order;
    @Spy
    MockHttpServletResponse httpServletResponse;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUsername("Test");
        order = new Order();
        order.setUser(user);
        Trip t = new Trip();
        order.setTrip(t);
        order.setContent("testContent");
        when(authenticationService.getUser((long) 100)).thenReturn(user);
        orderController = new OrderController(orderService, authenticationService);
    }

    @Test
    public void insert_Should_Return_Null_If_SessionIdIsNull_AndSet401Response() {
        Long sessionId = null;
        assertNull(orderController.insertOrder(sessionId, new Order(), httpServletResponse));
        assertEquals(httpServletResponse.getStatus(), HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    public void insert_should_Return_Order_If_SessionIdIsValid_AndNotSetResponse() {
        Long sessionId = Long.valueOf(100);
        OrderDto o = orderController.insertOrder(sessionId, order, httpServletResponse);
        assertEquals(o.content, order.getContent());
        assertNotEquals(httpServletResponse.getStatus(), HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    public void getAllOrders_should_Call_orderService_getOrders() {
        orderController.getAllOrders();
        verify(orderService, times(1)).getOrders();
    }

    @Test
    public void getAllOrders_should_Return_CorrectDtoResult() {
        List<Order> orders = new ArrayList<Order>();
        orders.add(order);
        when(orderService.getOrders()).thenReturn(orders);
        assertEquals("testContent", orderController.getAllOrders().get(0).content);

    }

    @Test
    public void getOrdersByTrip_should_Call_orderService_getOrdersByTrip() {
        orderController.getOrdersByTrip((long) 0);
        verify(orderService, times(1)).getOrdersByTrip((long) 0);
    }


}