package com.company;


public class OrderParser {
    public Order parseOrder(String s) {
        Order order = new Order();
        order.setContent(s);
        return order;

    }
}
