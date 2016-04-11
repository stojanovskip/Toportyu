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
    private OrderParser orderParser;

    ListHttpHandler(Interactor interactor, OrderParser orderParser) {
        this.orderParser = orderParser;
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
                interactor.newOrderArrived(orderParser.parseOrderString(body));
            } else {
                interactor.newOrderArrived(orderParser.parseOrderJson(body));
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