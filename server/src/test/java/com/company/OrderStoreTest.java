package com.company;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import org.junit.Test;

public class OrderStoreTest {

	@Test
	public void testSaveOrder() throws IOException {
		final Order originalOrder = new Order();
		IOrderTransformer orderParser = mock(IOrderTransformer.class);
		when(orderParser.toString(originalOrder)).thenReturn("asd");
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(stream);
		
		IOProvider ioProvider = mock(IOProvider.class);
		when(ioProvider.createWriter()).thenReturn(writer);
	
		OrderStore orderStore = new OrderStore(ioProvider, orderParser);
		orderStore.saveOrder(originalOrder);
		String string = new String(stream.toByteArray());
		assertEquals("asd" + System.getProperty("line.separator"), string);
	}

	@Test
	public void testSaveAndGet() throws IOException {
		Order order1 = new Order();
		order1.setContent("test");

		IOrderTransformer orderParser = new OrderTransformer();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(stream);
		
		IOProvider ioProvider = mock(IOProvider.class);
		when(ioProvider.createWriter()).thenReturn(writer);

		OrderStore orderStore = new OrderStore(ioProvider, orderParser);
		orderStore.saveOrder(order1);

		ByteArrayInputStream inputStream = new ByteArrayInputStream(stream.toByteArray());
		InputStreamReader reader = new InputStreamReader(inputStream);
		when(ioProvider.createReader()).thenReturn(reader);

		List<Order> orders = orderStore.getOrders();
		assertEquals(1, orders.size());
		assertEquals(order1.getContent(), orders.get(0).getContent());
	}

}
