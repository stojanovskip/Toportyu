package com.company;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Application {

    private HttpInput httpInput;
    private Interactor interactor;
    private Gson gson;

    Application() throws IOException {
        try {
            OrderParser orderParser = new OrderParser();
            OrderStore orderStore = new OrderStore(new PrintWriter(new FileWriter("orders.txt", true), true), orderParser);
            gson = new Gson();
            interactor = new Interactor(orderStore, orderParser, gson);
        } catch (Exception e) {
        }
        httpInput = new HttpInput(interactor);
    }

    void run() {
        httpInput.start();
    }

}