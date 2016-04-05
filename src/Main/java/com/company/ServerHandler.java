package com.company;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

class ServerHandler {
    private HttpExchange httpExchange;

    ServerHandler(HttpExchange newHttpExchange) {
        this.httpExchange = newHttpExchange;
    }

    String getRequestBody() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream requestBody = httpExchange.getRequestBody();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestBody));
        char[] charBuffer = new char[128];
        int bytesToRead;
        while ((bytesToRead = bufferedReader.read(charBuffer)) > 0) {
            stringBuilder.append(charBuffer, 0, bytesToRead);
        }
        return stringBuilder.toString();
    }

    void respond(int responseType, String message) throws Exception {
        OutputStream outStream = this.httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(responseType, message.length());
        outStream.write(message.getBytes());
        outStream.close();
    }

    boolean isPost() {
        return "POST".equals(httpExchange.getRequestMethod());
    }
    boolean isGet() {
        return "GET".equals(httpExchange.getRequestMethod());
    }
}

