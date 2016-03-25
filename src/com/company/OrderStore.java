package com.company;

import java.util.*;
import java.io.*;

public class OrderStore {

    private PrintWriter printWriter;

    public OrderStore(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void saveOrder(Order newOrder) {
        printWriter.println(newOrder.getContent());
        printWriter.flush();
    }

    public void close() {
        printWriter.close();
    }

}
