package com.company;

import java.io.*;

/**
 * Created by Madi.Yessirkepov on 3/21/2016.
 */
class Application implements Listener {
    private final OrderParser orderParser;
    private OrderStore orderStore;
    private HttpInput httpInput;

    Application() {

        orderParser = new OrderParser();
        try {
            httpInput = new HttpInput(this);
            orderStore = new OrderStore(new PrintWriter(new FileWriter("orders.txt", true), true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void run() {

        httpInput.start();

    }

    @Override
    public void newOrderArrived(String order) {
        orderStore.saveOrder(orderParser.parseOrder(order));
    }

}
