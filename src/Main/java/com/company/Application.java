package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Application implements Listener {
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
}