package com.company;


class OrderParser {

    Order parseOrder(String s) {
        Order order = new Order();
        order.setContent(s);
        return order;

    }
}
