package com.company;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by Andras.Timar on 3/29/2016.
 */
class ListHttpHandler implements HttpHandler {
    private Interactor interactor;
    private OrderTransformer orderTransformer;

    ListHttpHandler(Interactor interactor, OrderTransformer orderTransformer) {
        this.orderTransformer = orderTransformer;
        this.interactor = interactor;

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

    private void sendServerError(ServerHandler serverHandler, Exception e) {
        try {
            serverHandler.respond(500, e.getMessage());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    private void onPost(ServerHandler serverHandler) throws Exception {
        Headers requestHeaders = serverHandler.getRequestHeaders();
        String first = requestHeaders.getFirst("Content-type");
        try {
            String body = serverHandler.getRequestBody();
            if (first.contains("text/plain;")) {
                interactor.newOrderArrived(orderTransformer.parseStringOrder(body));
            } else {
                interactor.newOrderArrived(orderTransformer.parseJsonOrder(body));
            }
            serverHandler.respondJson(200, body);

        } catch (Exception ex) {
            sendServerError(serverHandler, ex);
        }

    }


    private void onGet(ServerHandler serverHandler) throws Exception {
        serverHandler.respondJson(200, new ResponseList(interactor.currentOrdersRequested()));
    }


}