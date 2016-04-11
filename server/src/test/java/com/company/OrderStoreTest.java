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
		final Order originalOrder = new Order();
		IOrderTransformer orderParser = new IOrderTransformer() {

			@Override
			public String toString(Order order) {
				assertEquals(originalOrder, order);
				return "asd";
			}

			@Override
			public Order fromString(String s) {
				throw new RuntimeException("Should not be called");
			}
			
		};
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
