package com.kerkyra.httpserver;

import com.kerkyra.datahandling.IOrderTransformer;
import com.kerkyra.application.Interactor;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static java.lang.Integer.parseInt;

/**
 * Created by Andras.Timar on 3/29/2016.
 */
class ListHttpHandler implements HttpHandler {
    private Interactor interactor;
    private com.kerkyra.datahandling.IOrderTransformer IOrderTransformer;

    ListHttpHandler(Interactor interactor, IOrderTransformer orderTransformer) {
        this.IOrderTransformer = orderTransformer;
        this.interactor = interactor;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        ServerHandler serverHandler = new ServerHandler(httpExchange);
        try {
            if (serverHandler.isPost()) {
                onPost(serverHandler);
            } else if (serverHandler.isGet()) {
                onGet(serverHandler);
            }
        } catch (Exception e) {
            sendServerError(serverHandler, e);
        }
    }

    private void sendServerError(ServerHandler serverHandler, Exception e) {
        try {
            serverHandler.respond(500, e.getMessage());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void onPost(ServerHandler serverHandler) throws Exception {
        Headers requestHeaders = serverHandler.getRequestHeaders();
        String first = requestHeaders.getFirst("Content-type");
        try {
            String body = serverHandler.getRequestBody();
            if (first.contains("text/plain;")) {
                interactor.newOrderArrived(IOrderTransformer.parseStringOrder(body));
            } else {
                interactor.newOrderArrived(IOrderTransformer.parseJsonOrder(body));
            }
            serverHandler.respondJson(200, body);

        } catch (Exception ex) {
            sendServerError(serverHandler, ex);
        }
    }

    private void onGet(ServerHandler serverHandler) throws Exception {
        serverHandler.respondJson(200, interactor.currentOrdersRequested());
    }

}