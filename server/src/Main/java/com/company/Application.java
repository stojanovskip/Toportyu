package com.company;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Application {
    private HttpInput httpInput;

    private OrderParser orderParser;

    Application() throws IOException {
        try {
            Interactor interactor;
            Gson gson = new Gson();
            orderParser = new OrderParser(gson);
            OrderStore orderStore = new OrderStore(new PrintWriter(new FileWriter("orders.txt", true), true), orderParser);
            interactor = new Interactor(orderStore, orderParser);
            httpInput = new HttpInput(interactor, orderParser);

        } catch (Exception e) {
        }
    }

    void run() {
        httpInput.start();
    }

}