package com.company;

import com.google.inject.Inject;

import javax.persistence.*;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by Andras.Timar on 4/18/2016.
 */

@Entity
@NamedQueries(
        @NamedQuery(
                name="Orders.findAll",
                query="SELECT * FROM orders"
        ))
public class OrderStoreDB implements IOrderStore {

    private EntityManager entityManager;
    @Inject
    public OrderStoreDB(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void saveOrder(Order newOrder) throws IOException {
        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

        entityManager.persist(newOrder);

        tx.commit();
    }

    @Override
    public List<Order> getOrders() throws IOException {
        List<Order> orders = new ArrayList<Order>();

        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

        Query query = entityManager.createNativeQuery("Select * from orders");
        orders = query.getResultList();

        tx.commit();

        return orders;
        }


    @Override
    public int orderCount() {

        int count = (int) entityManager.createNativeQuery("SELECT count(*) FROM orders").getSingleResult();

        return count;
    }
}