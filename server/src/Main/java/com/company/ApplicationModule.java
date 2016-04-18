package com.company;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Created by Andras.Timar on 4/12/2016.
 */
public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IOProvider.class).toInstance(new FileBasedIOProvider("orders.txt"));
        bind(IOrderTransformer.class).toInstance(new OrderTransformer(new Gson()));
        bind(IOrderStore.class).toInstance(new OrderStoreDB());
    }
    @Singleton
    @Provides
    Connection connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://sql.liligo.com" +
                    "user=toportyu&password=t0p0rtyu");
            System.out.println("success");
        } catch (SQLException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
        return conn;
    }

}
