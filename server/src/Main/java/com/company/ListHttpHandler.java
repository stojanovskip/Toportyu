package com.company;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Integer.parseInt;

/**
 * Created by Andras.Timar on 3/29/2016.
 */
class ListHttpHandler implements HttpHandler {
    private Interactor interactor;
    private IOrderTransformer IOrderTransformer;
    Lock lock = new ReentrantLock();


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
            } else if (serverHandler.isOptions()) {
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
        Thread thread = new Thread(new AsyncPoller(serverHandler));
        thread.start();
    }

    class AsyncPoller implements Runnable {

        private final ServerHandler serverHandler;

        public AsyncPoller(ServerHandler serverHandler) {
            this.serverHandler = serverHandler;
        }

        @Override
        public void run() {
            Headers requestHeaders = serverHandler.getRequestHeaders();
            int currentLength = parseInt(requestHeaders.getFirst("CurrentLength"));

            try {
                try {
                    int counter = 0;
                    lock.lock();
                    int currentLocalLength = interactor.currentOrdersRequested().size();
                    lock.unlock();
                    while (counter < 20 && currentLength == currentLocalLength) {
                        Thread.sleep(500);
                        lock.lock();
                        currentLocalLength = interactor.currentOrdersRequested().size();
                        lock.unlock();
                        counter++;
                    }
                    serverHandler.respondJson(200, new ResponseList(interactor.currentOrdersRequested()));
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                    serverHandler.respond(500, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}