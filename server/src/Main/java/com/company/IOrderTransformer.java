package com.company;

/**
 * Created by bandi on 4/11/2016.
 */
public interface IOrderTransformer {
    Order parseJsonOrder(String body);

    String toString(Order order);

    Order parseStringOrder(String body);

    String toJson(Order order);
}
