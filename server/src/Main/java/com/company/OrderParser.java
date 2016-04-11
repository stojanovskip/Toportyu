package com.company;

import com.google.gson.Gson;

class OrderParser {
    private Gson gson;

    public OrderParser(Gson gson) {
        this.gson = gson;
    }

    Order parseJsonOrder(String body) {
        Order order = gson.fromJson(body, Order.class);
        return order;
    }

    Order parseStringOrder(String body) {
        Order order = new Order();
        order.setContent(body);
        return order;
    }

    String toString(Order order) {
        return order.getContent();
    }

}
