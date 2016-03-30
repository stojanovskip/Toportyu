package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

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
        OutputStream outStream = httpExchange.getResponseBody();
        BufferedReader bufferedReader;
        try {
            if (httpExchange.getRequestMethod().equals("POST")) {
                StringBuilder stringBuilder = new StringBuilder();
                InputStream requestBody = httpExchange.getRequestBody();
                bufferedReader = new BufferedReader(new InputStreamReader(requestBody));
                char[] charBuffer = new char[128];
                int bytesToRead;
                while ((bytesToRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesToRead);
                }
                listener.newOrderArrived(stringBuilder.toString());
                String responseMessage = "Order saved";
                httpExchange.sendResponseHeaders(200, responseMessage.length());
                outStream.write(responseMessage.getBytes());
            } else {
                String responseMessage = "Reached GET handler successfully";
                httpExchange.sendResponseHeaders(200, responseMessage.length());
                outStream.write(responseMessage.getBytes());
            }
        } catch (Exception e) {
            String responseMessage = e.getMessage();
            httpExchange.sendResponseHeaders(500, responseMessage.length());
            outStream.write(responseMessage.getBytes());
        } finally {
            outStream.close();
        }
    }
}