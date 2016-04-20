package com.company;

import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by Andras.Timar on 4/18/2016.
 */
public class OrderStoreDB implements IOrderStore {


   /*private EntityManager entityManager;
    @Inject
    public OrderStoreDB(EntityManager entityManager) {
       this.entityManager = entityManager;
    }
*/
    @Override
    public void saveOrder(Order newOrder) throws IOException {


    }

    @Override
    public List<Order> getOrders() throws IOException {

        List<Order> orders = new ArrayList<Order>();

            return orders;
    }

    @Override
    public int orderCount() {
        ResultSet rs = null;
        int count = 0;
        try {
           // rs = (ResultSet) this.entityManager.createNamedQuery("select count(*) as num from toportyu.orders");
            rs.next();
            count = parseInt(rs.getString("num"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }
}