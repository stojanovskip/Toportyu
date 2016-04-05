package com.company;
import java.util.List;
/**
 * Created by Andras.Timar on 3/25/2016.
 */
interface Interactor {
    void newOrderArrived(String order);
    List<Order> currentOrdersRequested();
}
