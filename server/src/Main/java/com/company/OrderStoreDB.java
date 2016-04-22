package com.company;

import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import java.io.IOException;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andras.Timar on 4/18/2016.
 */


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
        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o",Order.class);
        orders = query.getResultList();

        tx.commit();

        return orders;
    }


    @Override
    public int orderCount() {
        int count = 0;
        Query query = entityManager.createNativeQuery("SELECT * FROM orders");
        count = query.getResultList().size();
        return count;
    }
}