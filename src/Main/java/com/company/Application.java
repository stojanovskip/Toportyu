package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

class Application implements Interactor {
    private final OrderParser orderParser;
    private OrderStore orderStore;
    private HttpInput httpInput;

    Application() throws IOException {
        orderParser = new OrderParser();
        httpInput = new HttpInput(this);
        orderStore = new OrderStore(new PrintWriter(new FileWriter("orders.txt", true), true));
    }

    void run() {
        httpInput.start();
    }

    @Override
    public void newOrderArrived(String order) {
        orderStore.saveOrder(orderParser.parseOrder(order));
    }

    @Override
    public List<Order> currentOrdersRequested() {
        try {
            return orderStore.getOrders();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}