package com.company;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.sun.xml.internal.rngom.util.Uri;

import java.io.OutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.URIParameter;
import java.util.concurrent.Executor;

/**
 * Created by bandi on 26/03/16.
 */
public class HttpInput {
    private HttpServer httpServer;
    private Listener listener;

    public HttpInput(Listener listener) throws IOException {
        this.listener = listener;
        httpServer = HttpServer.create(new InetSocketAddress(6421), 0);
        httpServer.createContext("/", new MyHandler());
        httpServer.setExecutor(null);
    }

    public void start() {
        httpServer.start();
    }

    public class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            try {
                String message = httpExchange.getRequestURI().getQuery().split("message=")[1];
                listener.newOrderArrived(message);
                String responsemssage = "Order saved";
                httpExchange.sendResponseHeaders(200, responsemssage.length());
                OutputStream op = httpExchange.getResponseBody();
                op.write(responsemssage.getBytes());
                op.close();
            } catch (Exception e) {
                String responsemssage = e.getMessage();
                httpExchange.sendResponseHeaders(500, responsemssage.length());
                OutputStream op = httpExchange.getResponseBody();
                op.write(responsemssage.getBytes());
                op.close();
            }
        }
    }
}
