package com.company;

import com.sun.net.httpserver.HttpHandler;

import java.io.OutputStream;

/**
 * Created by Andras.Timar on 3/29/2016.
 */
abstract class MyHttpHandler implements HttpHandler {
    final Listener listener;
    OutputStream outStream;

    MyHttpHandler(Listener listener) {
        this.listener = listener;
    }
}
