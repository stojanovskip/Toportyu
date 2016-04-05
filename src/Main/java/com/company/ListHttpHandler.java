package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

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
        ServerHandler serverHandler = new ServerHandler(httpExchange);
        try {
            if (serverHandler.isPost()) {
                onPost(serverHandler);
            }
            if (serverHandler.isGet()) {
                onGet(serverHandler);
            }
        } catch (Exception e) {
            sendServerError(serverHandler, e);
        }
    }

    private void sendServerError(ServerHandler serverHandler, Exception e) throws IOException {
        try {
            serverHandler.respond(500, e.getMessage());
        } catch (Exception e1) {
            throw new IOException(e1);
        }
    }

    private void onPost(ServerHandler serverHandler) throws Exception {
        String body = serverHandler.getRequestBody();
        listener.newOrderArrived(body);
        serverHandler.respond(200, body);
    }

    private void onGet(ServerHandler serverHandler) throws Exception {
        try {
            serverHandler.respondJson(200, new Response(listener.currentOrdersRequested()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Response {
        private List<Order> orderList;

        public Response(List<Order> orderList) {
            this.orderList = orderList;
        }

        public List<Order> getOrderList() {
            return orderList;
        }
    }
}