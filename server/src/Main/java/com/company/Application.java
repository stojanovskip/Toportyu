package com.company;

import com.google.gson.Gson;

class Application {
    private HttpInput httpInput;

    private IOrderTransformer IOrderTransformer;

    Application() throws Exception {
            FileBasedIOProvider fileBasedIOProvider = new FileBasedIOProvider();
            Interactor interactor;
            Gson gson = new Gson();
            IOrderTransformer = new OrderTransformer(gson);
            OrderStore orderStore = new OrderStore(IOrderTransformer, fileBasedIOProvider);
            interactor = new Interactor(orderStore);
            httpInput = new HttpInput(interactor, IOrderTransformer);

    }

    void run() {
        httpInput.start();
    }

}