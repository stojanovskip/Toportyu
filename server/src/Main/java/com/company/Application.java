package com.company;

import com.google.gson.Gson;

class Application {
    private HttpInput httpInput;

    private OrderTransformer orderTransformer;

    Application() throws Exception {

            Interactor interactor;
            Gson gson = new Gson();
            orderTransformer = new OrderTransformer(gson);
            OrderStore orderStore = new OrderStore("orders.txt", orderTransformer);
            interactor = new Interactor(orderStore, orderTransformer);
            httpInput = new HttpInput(interactor, orderTransformer);

    }

    void run() {
        httpInput.start();
    }

}