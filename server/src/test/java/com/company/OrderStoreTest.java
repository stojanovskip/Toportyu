package com.company;

import org.junit.Test;

import java.io.*;
import java.util.List;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Andras.Timar on 4/12/2016.
 */
public class OrderStoreTest {
    @Test
    public void saveOrder() throws IOException {
        final Order origOrder = new Order();
        origOrder.setContent("testdata");

        IOrderTransformer orderTransformer = mock(IOrderTransformer.class);
        when(orderTransformer.toString(origOrder)).thenReturn("testdata");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(stream);

        IOProvider ioProvider = mock(IOProvider.class);
        when(ioProvider.createWriter()).thenReturn(writer);

        OrderStore orderStore = new OrderStore(orderTransformer, ioProvider);
        orderStore.saveOrder(origOrder);
        String string = new String(stream.toByteArray());
        assertEquals("testdata" + System.getProperty("line.separator"), string);


    }

    @Test
    public void getOrders() throws IOException {
        final Order origOrder = new Order();
        final Order origOrder2 = new Order();
        IOrderTransformer orderTransformer = mock(IOrderTransformer.class);
        when(orderTransformer.parseStringOrder("testdata")).thenReturn(origOrder);
        when(orderTransformer.parseStringOrder("testdata2")).thenReturn(origOrder2);

        IOProvider ioProvider = mock(IOProvider.class);
        String newLine = System.getProperty("line.separator");

        Reader reader = new StringReader("testdata" + newLine + "testdata2");
        when(ioProvider.createReader()).thenReturn(reader);

        OrderStore orderStore = new OrderStore(orderTransformer, ioProvider);

        List<Order> testlist = orderStore.getOrders();
        assertEquals(2, testlist.size());
        assertEquals(origOrder.getContent(), testlist.get(0).getContent());
        assertEquals(origOrder2.getContent(), testlist.get(1).getContent());
    }
}