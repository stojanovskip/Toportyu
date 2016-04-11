package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

class OrderStore {

    private final PrintWriter printWriter;
    private IOrderTransformer orderTransformer;
	private IOProvider ioProvider;

	@Inject
    OrderStore(IOProvider ioProvider, IOrderTransformer orderParser) throws IOException {
        this.ioProvider = ioProvider;
		this.printWriter = ioProvider.createWriter();
        this.orderTransformer = orderParser;
    }

    void saveOrder(Order newOrder) {
        printWriter.println(orderTransformer.toString(newOrder));
        printWriter.flush();
    }

	public List<Order> getOrders() throws IOException {
        List<Order> orderList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(ioProvider.createReader());
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            Order order = orderTransformer.fromString(line);
            orderList.add(order);
        }

        return orderList;
    }
}