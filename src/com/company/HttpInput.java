package com.company;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;

/**
 * Created by bandi on 26/03/16.
 */
class HttpInput {
    private HttpServer httpServer;

    HttpInput(Listener listener) throws IOException {
        int port = 8000;
        httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        httpServer.createContext("/", new HttpPostHandler(listener));

        httpServer.setExecutor(null);
    }

    void start() {
        httpServer.start();
    }

}
