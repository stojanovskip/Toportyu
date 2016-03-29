package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;

/**
 * Created by bandi on 26/03/16.
 */
public class HttpInput {
    private HttpServer httpServer;
    private Listener listener;

    public HttpInput(Listener listener) throws IOException {
        this.listener = listener;
        httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
        httpServer.createContext("/", new HttpPostHandler(listener));
        httpServer.setExecutor(null);
    }

    public void start() {
        httpServer.start();
    }

}
