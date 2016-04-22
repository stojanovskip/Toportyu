package com.kerkyra;

import com.kerkyra.application.Interactor;
import com.kerkyra.datahandling.IOrderStore;
import com.kerkyra.datahandling.Order;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Madi.Yessirkepov on 4/22/2016.
 */
public class InteractorTest {
    private IOrderStore orderStore;
    private Interactor interactor;

    @Before
    public void setUp() throws Exception {
        orderStore = mock(IOrderStore.class);
        interactor = new Interactor(orderStore);
    }

    @Test
    public void newOrderArrivedTest() throws IOException {
        Order newOrder = new Order();
        interactor.newOrderArrived(newOrder);
        verify(orderStore).saveOrder(newOrder);
    }

    @Test
    public void currentOrdersRequestedTest() throws IOException {
        List<Order> resultList = new ArrayList<>();

        Order order = new Order();
        order.setContent("1");
        resultList.add(order);

        when(orderStore.getOrders()).thenReturn(resultList);
        List<Order> orderList = interactor.currentOrdersRequested();

        assertEquals("1", orderList.get(0).getContent() + "");
        assertEquals(resultList, orderList);
    }

}
