package com.company;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by bandi on 26/03/16.
 */
class HttpInput {
    private final HttpServer httpServer;
    private final int PORT = 6543;

    HttpInput(Listener listener) throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/", new ListHttpHandler(listener));
        httpServer.setExecutor(null);
    }

    void start() {
        httpServer.start();
        System.out.println("Server is up and listening in " + PORT);
    }

}
