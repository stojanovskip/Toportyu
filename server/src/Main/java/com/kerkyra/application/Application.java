package com.kerkyra.application;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kerkyra.httpserver.HttpInput;

class Application {
    private HttpInput httpInput;

    Application() throws Exception {
        Injector injector = Guice.createInjector(new ApplicationModule());
        httpInput = injector.getInstance(HttpInput.class);
    }

    void run() {
        httpInput.start();
    }

}