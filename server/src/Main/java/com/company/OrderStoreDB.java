package com.company;

import com.google.inject.Inject;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andras.Timar on 4/18/2016.
 */
public class OrderStoreDB implements IOrderStore {

    private Connection connection;

    @Inject
    public OrderStoreDB() {

        try {
            String username = "toportyu";
            String password = "t0p0rtyu";
            String url = "jdbc:mysql://sql.liligo.com";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("success");
        } catch (SQLException e) {
            System.out.println("Fail");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveOrder(Order newOrder) throws IOException {

    }

    @Override
    public List<Order> getOrders() throws IOException {
        Statement statement = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<Order>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM toportyu.Orders");
            while(rs.next())
            {
                Order o = new Order();
                o.setContent(rs.getString("order"));
                o.setCost(rs.getInt("cost"));
                orders.add(o);
            }

            System.out.println("yay");
        } catch (SQLException e) {
            System.out.println("fail");
            e.printStackTrace();
        }
        finally {
            if(rs!=null)
            {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }



            return orders;

        }
    }
}
