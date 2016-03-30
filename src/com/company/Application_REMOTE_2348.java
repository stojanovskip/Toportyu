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

    public Application() {
        orderParser = new OrderParser();
        consoleInput = new ConsoleInput(this);
        try {
            orderStore = new OrderStore(new PrintWriter(new FileWriter("orders.txt", true), true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (!interrupted) {
            try {
                consoleInput.ReadInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
