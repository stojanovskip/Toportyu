package com.company;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.junit.Test;

public class OrderStoreTest {

	@Test
	public void testSaveOrder() {
		IOrderTransformer orderParser = new OrderTransformer();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(stream);
		OrderStore orderStore = new OrderStore(writer, orderParser);
		Order order = new Order();
		order.setContent("test");
		orderStore.saveOrder(order);
		String string = new String(stream.toByteArray());
		assertEquals("test" + System.getProperty("line.separator"), string);
	}

	@Test
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
