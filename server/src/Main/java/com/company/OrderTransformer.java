package com.company;

import com.google.gson.Gson;

class OrderTransformer implements IOrderTransformer {
    private Gson gson;


    public OrderTransformer(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Order parseJsonOrder(String body) {
        Order order = gson.fromJson(body, Order.class);
        return order;
    }

    @Override
    public Order parseStringOrder(String body) {
        Order order = new Order();
        order.setContent(body);
        return order;
    }

    @Override
    public String toString(Order order) {
        return order.getContent();
    }

}
