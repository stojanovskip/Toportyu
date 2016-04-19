package com.company;

import com.google.inject.Inject;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by Andras.Timar on 4/18/2016.
 */
public class OrderStoreDB implements IOrderStore {

    private Connection connection;

    @Inject
    public OrderStoreDB(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveOrder(Order newOrder) throws IOException {
        try {
            Statement statement = connection.createStatement();
            StringBuilder sql = new StringBuilder();
            sql.append("insert into toportyu.orders(content,cost) values('");
            sql.append(newOrder.getContent());
            sql.append("',");
            sql.append(newOrder.getCost());
            sql.append(")");
            System.out.println(sql.toString());
            statement.execute(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<Order> getOrders() throws IOException {
        ResultSet rs = null;
        List<Order> orders = new ArrayList<Order>();
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM toportyu.orders");
            while (rs.next()) {
                Order o = new Order();
                o.setContent(rs.getString("content"));
                o.setCost(rs.getInt("cost"));
                orders.add(o);
            }
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


            return orders;

        }
    }

    @Override
    public int orderCount() {
        ResultSet rs = null;
        int count = 0;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select count(*) as num from toportyu.orders");
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