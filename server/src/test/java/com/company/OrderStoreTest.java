package com.company;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.junit.Test;

public class OrderStoreTest {

	@Test
	public void testSaveOrder() {
		final Order originalOrder = new Order();
		IOrderTransformer orderParser = mock(IOrderTransformer.class);
		when(orderParser.toString(originalOrder)).thenReturn("asd");
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(stream);
		OrderStore orderStore = new OrderStore(writer, orderParser);
		orderStore.saveOrder(originalOrder);
		String string = new String(stream.toByteArray());
		assertEquals("asd" + System.getProperty("line.separator"), string);
	}

	//@Test
	public void testSaveAndGet() throws IOException {
		Order order1 = new Order();
		order1.setContent("test");

		IOrderTransformer orderParser = new OrderTransformer();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(stream);
		OrderStore orderStore = new OrderStore(writer, orderParser);

		orderStore.saveOrder(order1);

		List<Order> orders = orderStore.getOrders();
		assertEquals(1, orders.size());
		assertEquals(order1.getContent(), orders.get(0).getContent());
	}

}
