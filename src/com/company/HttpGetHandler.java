package com.company;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

/**
 * Created by Andras.Timar on 3/29/2016.
 */
public class HttpGetHandler extends MyHttpHandler {

    public HttpGetHandler(Listener listener) {
        super(listener);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try
        {

        }
        catch(Exception ex)
        {

        }
    }
}
