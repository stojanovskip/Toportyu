package com.company;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderParserTest {

	@Test
	public void test() {
		IOrderTransformer orderParser = new OrderTransformer();
		Order order = orderParser.fromString("test content");
		assertEquals("test content", order.getContent());
	}

}
