package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Application {

    private HttpInput httpInput;
    private Interactor interactor;

    Application() throws IOException {
        try {
            OrderParser orderParser = new OrderParser();
            OrderStore orderStore = new OrderStore(new PrintWriter(new FileWriter("orders.txt", true), true), orderParser);
            interactor = new Interactor(orderStore, orderParser);
        } catch (Exception e) {
        }
        httpInput = new HttpInput(interactor);
    }

    void run() {
        httpInput.start();
    }

}