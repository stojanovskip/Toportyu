package com.company;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Andras.Timar on 4/12/2016.
 */
public class OrderStoreTest {

    @Test
    public void saveOrder() throws IOException {
        final Order origOrder = new Order();
        origOrder.setContent("testdata");

        IOrderTransformer orderTransformer = mock(IOrderTransformer.class);
        when(orderTransformer.toJson(origOrder)).thenReturn("testdata");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(stream);

        IOProvider ioProvider = mock(IOProvider.class);
        when(ioProvider.createWriter()).thenReturn(writer);

        OrderStore orderStore = new OrderStore(orderTransformer, ioProvider);
        orderStore.saveOrder(origOrder);
        String string = new String(stream.toByteArray());
        assertEquals("testdata"+System.getProperty("line.separator"), string);

    }

    @Test
    public void getOrders() throws IOException {
        final Order origOrder = new Order();
        final Order origOrder2 = new Order();
        IOrderTransformer orderTransformer = mock(IOrderTransformer.class);
        when(orderTransformer.parseJsonOrder("testdata")).thenReturn(origOrder);
        when(orderTransformer.parseJsonOrder("testdata2")).thenReturn(origOrder2);

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

    @Test
    public void testSaveAndGetOrders() throws IOException {

        final int numOrders = 10;
        Order[] resOrders = new Order[numOrders];
        IOrderTransformer transformOrder = mock(IOrderTransformer.class);
        for (int i = 0; i < numOrders; i++) {
            resOrders[i] = new Order();
            resOrders[i].setContent("Message" + (i + 1));
            when(transformOrder.parseJsonOrder("Message" + (i + 1))).thenReturn(resOrders[i]);
        }

        String endLine = System.getProperty("line.separator");
        String temp = "";
        IOProvider ioProvider = mock(IOProvider.class);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintWriter print = new PrintWriter(stream);
        when(ioProvider.createWriter()).thenReturn(print);

        OrderStore testStore = new OrderStore(transformOrder, ioProvider);

        for (int i = 0; i < numOrders; i++) {
            testStore.saveOrder(resOrders[i]);
            temp += resOrders[i].getContent() + endLine;
        }

        Reader reader = new StringReader(temp);
        when(ioProvider.createReader()).thenReturn(reader);

        List<Order> expectedString = new ArrayList<>();
        for (int i = 0; i < numOrders; i++) {
            expectedString.add(transformOrder.parseJsonOrder("Message" + (i + 1)));
        }

        List<Order> listOfOrders = testStore.getOrders();

        assertEquals(10, listOfOrders.size());
        assertEquals("Message1", listOfOrders.get(0).getContent());
        assertEquals(expectedString, listOfOrders);
    }

}