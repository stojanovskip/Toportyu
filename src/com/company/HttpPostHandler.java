package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

/**
 * Created by Andras.Timar on 3/29/2016.
 */
public class HttpPostHandler implements HttpHandler {

    private Listener listener;

    public HttpPostHandler(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        BufferedReader bufferedReader = null;
        try {
            if (httpExchange.getRequestMethod().equals("POST")) {

                StringBuilder stringBuilder = new StringBuilder();
                InputStream requestBody = httpExchange.getRequestBody();
                bufferedReader = new BufferedReader(new InputStreamReader(requestBody));
                char[] buffer = new char[128];
                int bytesToRead = -1;
                while ((bytesToRead = bufferedReader.read(buffer)) > 0)
                    stringBuilder.append(buffer, 0, bytesToRead);

                listener.newOrderArrived(stringBuilder.toString());
                String responseMessage = "Order saved";
                httpExchange.sendResponseHeaders(200, responseMessage.length());
                OutputStream op = httpExchange.getResponseBody();
                op.write(responseMessage.getBytes());
                op.close();
            } else throw new Exception("Need to use POST");
        } catch (Exception e) {
            String responseMessage = e.getMessage();
            httpExchange.sendResponseHeaders(500, responseMessage.length());
            OutputStream op = httpExchange.getResponseBody();
            op.write(responseMessage.getBytes());
            op.close();
        }

    }
}