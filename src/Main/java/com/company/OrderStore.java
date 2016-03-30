package com.company;

import java.io.PrintWriter;

class OrderStore {

    private final PrintWriter printWriter;

    OrderStore(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    void saveOrder(Order newOrder) {
        printWriter.println(newOrder.getContent());
        printWriter.flush();
    }

}