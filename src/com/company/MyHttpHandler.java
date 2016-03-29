package com.company;

import com.sun.net.httpserver.HttpHandler;

import java.io.OutputStream;

/**
 * Created by Andras.Timar on 3/29/2016.
 */
public abstract class MyHttpHandler implements HttpHandler {
    Listener listener;
    OutputStream op;
    public MyHttpHandler(Listener listener)
    {
        this.listener = listener;
    }
}
