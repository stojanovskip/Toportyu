package com.company;

import java.io.*;

/**
 * Created by Madi.Yessirkepov on 3/21/2016.
 */
class Application implements Listener {
    private boolean interrupted = false;
    private final OrderParser orderParser;
    private final ConsoleInput consoleInput;
    private OrderStore orderStore;

    Application() throws IOException {
        orderParser = new OrderParser();
        consoleInput = new ConsoleInput(this);
        orderStore = new OrderStore(new PrintWriter(new FileWriter("orders.txt", true), true));
    }

    void run() {
        try {
            while (!interrupted) {
                consoleInput.ReadInput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            orderStore.close();
        }
    }

    @Override
    public void newOrderArrived(String order) {
        orderStore.saveOrder(orderParser.parseOrder(order));
    }

    @Override
    public void onQuitRequest() {
        interrupted = true;
    }
}
