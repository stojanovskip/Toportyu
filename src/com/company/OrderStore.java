package com.company;
import java.util.*;

public class OrderStore {

    Order order = new Order();
    List<String> OrderList= new ArrayList<String>();

    public void addOrder(String newOrder)
    {
        order.setContent(newOrder);
        OrderList.add(order.getContent());
    }
}
