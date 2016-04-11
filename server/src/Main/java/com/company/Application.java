package com.company;

import java.io.IOException;

import com.google.gson.Gson;

class Application {

    private HttpInput httpInput;
    private Interactor interactor;
    private Gson gson;

    Application() throws IOException {
        try {
        	IOProvider ioProvider = new FilesBasedIOProvider("orders.txt");
            IOrderTransformer orderParser = new OrderTransformer();
            OrderStore orderStore = new OrderStore(ioProvider, orderParser);
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