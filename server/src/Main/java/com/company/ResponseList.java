package com.company;

import java.util.List;

/**
 * Created by Andras.Timar on 4/11/2016.
 */
public class ResponseList {
    private List<Order> orderList;

    public ResponseList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}