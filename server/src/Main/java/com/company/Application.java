package com.company;

import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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