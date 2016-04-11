package com.company;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.inject.Inject;

import com.sun.net.httpserver.HttpServer;

/**
 * Created by bandi on 26/03/16.
 */
class HttpInput {
    private final HttpServer httpServer;
    private final int PORT = 8000;

    @Inject
    HttpInput(Interactor interactor) throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/orders", new ListHttpHandler(interactor));
        httpServer.setExecutor(null);
    }

    void start() {
        httpServer.start();
        System.out.println("Server is up and listening in " + PORT);
    }

}
