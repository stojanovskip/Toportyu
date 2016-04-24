package com.kerkyra.httpserver;

import com.google.inject.Inject;
import com.kerkyra.datahandling.IOrderTransformer;
import com.kerkyra.application.Interactor;
import com.sun.net.httpserver.HttpServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;

import static java.lang.Integer.parseInt;

/**
 * Created by bandi on 26/03/16.
 */
public class HttpInput {
    private final HttpServer httpServer;
    private int PORT;

    @Inject
    HttpInput(Interactor interactor, IOrderTransformer orderTransformer) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/Main/resources/port.config"));
        PORT = parseInt(properties.getProperty("port"));
        httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/orders", new ListHttpHandler(interactor, orderTransformer));
        httpServer.setExecutor(null);
    }

    public void start() {
        httpServer.start();
        System.out.println("Server is up and listening on " + PORT);
    }
}
