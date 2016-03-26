package com.company;

import java.io.*;

/**
 * Created by Madi.Yessirkepov on 3/21/2016.
 */
public class Application implements Listener {
    public boolean interrupted = false;
    OrderParser orderParser;
    ConsoleInput consoleInput;
    OrderStore orderStore;
    HttpInput httpInput;

    public Application() {

        orderParser = new OrderParser();
        try {
            httpInput = new HttpInput(this);
            orderStore = new OrderStore(new PrintWriter(new FileWriter("orders.txt", true), true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {

        httpInput.start();

    }

    @Override
    public void newOrderArrived(String order) {
        orderStore.saveOrder(orderParser.parseOrder(order));
    }

    @Override
    public void userWantsToQuit() {
        interrupted = true;
    }
}
