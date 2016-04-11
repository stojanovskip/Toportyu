package com.company;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderParserTest {

	@Test
	public void test() {
		OrderParser orderParser = new OrderParser();
		Order order = orderParser.parseOrder("test content");
		assertEquals("test content", order.getContent());
	}

}
