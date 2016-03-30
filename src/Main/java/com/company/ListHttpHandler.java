package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by Andras.Timar on 3/29/2016.
 */
class ListHttpHandler implements HttpHandler {
    private Listener listener;

    ListHttpHandler(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        ServerHandling serverHandling = new ServerHandling(httpExchange);
        try {
            if (serverHandling.isPost()) {
                onPost(serverHandling);
            }
        } catch (Exception e) {
            sendServerError(serverHandling, e);
        }
    }

    private void sendServerError(ServerHandling serverHandling, Exception e) throws IOException {
        try {
            serverHandling.response(500, e.getMessage());
        } catch (Exception e1) {
            throw new IOException(e1);
        }
    }

    private void onPost(ServerHandling serverHandling) throws Exception {
        String body = serverHandling.getRequestBody();
        listener.newOrderArrived(body);
        serverHandling.response(200, body);
    }
}