package com.kerkyra.httpserver;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ServerHandler {
    private HttpExchange httpExchange;

    public ServerHandler(HttpExchange newHttpExchange) {
        this.httpExchange = newHttpExchange;
        this.requestMethod  = httpExchange.getRequestMethod();
    }
    String requestMethod;


    public String getRequestBody() throws Exception {
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

    public void respondJson(int statusCode, Object response) throws Exception {
        httpExchange.getResponseHeaders().add("Content-Type", "application/json");
        respond(statusCode, new Gson().toJson(response));
    }

    public Headers getRequestHeaders() {
        return httpExchange.getRequestHeaders();
    }

    public Headers getResponseHeaders() {
        return httpExchange.getResponseHeaders();
    }

    public void respond(int responseType, String message) throws Exception {
        OutputStream outStream = this.httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(responseType, message.length());
        outStream.write(message.getBytes());
        outStream.close();
    }

    public boolean isPost() {
        return "POST".equals(requestMethod);
    }

    public boolean isGet() {
        return "GET".equals(requestMethod);
    }

}

