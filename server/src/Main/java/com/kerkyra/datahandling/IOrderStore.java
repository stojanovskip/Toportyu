package com.kerkyra.datahandling;

import java.io.IOException;
import java.util.List;

/**
 * Created by Andras.Timar on 4/18/2016.
 */
public interface IOrderStore {
    void saveOrder(Order newOrder) throws IOException;

    List<Order> getOrders() throws IOException;

}
