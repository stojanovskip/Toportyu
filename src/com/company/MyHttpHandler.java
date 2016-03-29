package com.company;

import com.sun.net.httpserver.HttpHandler;

/**
 * Created by Andras.Timar on 3/29/2016.
 */
public abstract class MyHttpHandler implements HttpHandler {
    Listener listener;
    public MyHttpHandler(Listener listener)
    {
        this.listener = listener;
    }
}
