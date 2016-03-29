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
        op = httpExchange.getResponseBody();
        try
        {
            if(httpExchange.getRequestMethod().equals("GET"))
            {
                String responseMessage = "Reached GET handler succesfully";
                httpExchange.sendResponseHeaders(200, responseMessage.length());
                op.write(responseMessage.getBytes());

            }
            else throw new Exception("This is the GET handler, you should use GET");
        }
        catch(Exception ex)
        {
            String responseMessage = ex.getMessage();
            httpExchange.sendResponseHeaders(500, responseMessage.length());
            op.write(responseMessage.getBytes());
        }
        finally {
            op.close();
        }
    }
}
