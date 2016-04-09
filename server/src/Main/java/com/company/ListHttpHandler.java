package com.company;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;

/**
 * Created by Andras.Timar on 3/29/2016.
 */
class ListHttpHandler implements HttpHandler {
    private Interactor interactor;

    ListHttpHandler(Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        ServerHandler serverHandler = new ServerHandler(httpExchange);
        //serverHandler.setAllowCrossOrigin("POST, GET, OPTIONS");
        try {
            if (serverHandler.isPost()) {

                onPost(serverHandler);

            }
            if (serverHandler.isGet()) {
                onGet(serverHandler);
            }
            if (serverHandler.isOptions()) {
                onOptions(serverHandler);
            }
        } catch (Exception e) {
            sendServerError(serverHandler, e);
        }
    }

    private void onOptions(ServerHandler serverHandler) throws Exception {
        serverHandler.respond(200, "");
    }

    private void sendServerError(ServerHandler serverHandler, Exception e) throws IOException {
        try {
            serverHandler.respond(500, e.getMessage());
        } catch (Exception e1) {
            throw new IOException(e1);
        }
    }

    private void onPost(ServerHandler serverHandler) throws Exception {
        Headers requestHeaders = serverHandler.getRequestHeaders();
        String first = requestHeaders.getFirst("Content-type");
        try {
            String body = serverHandler.getRequestBody();
            if (first.contains("text/plain;")) {
                interactor.newOrderArrived(body);
            } else {
                interactor.newJsonOrderArrived(body);
            }
            serverHandler.respondJson(200, body);

        } catch (Exception ex) {
            sendServerError(serverHandler, ex);
        }

    }


    private void onGet(ServerHandler serverHandler) throws Exception {
        serverHandler.respondJson(200, new SendResponseList(interactor.currentOrdersRequested()));
    }

    public static class SendResponseList {
        private List<Order> orderList;

        public SendResponseList(List<Order> orderList) {
            this.orderList = orderList;
        }

        public List<Order> getOrderList() {
            return orderList;
        }
    }
}