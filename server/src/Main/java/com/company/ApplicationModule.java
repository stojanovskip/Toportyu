package com.company;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Andras.Timar on 4/12/2016.
 */
public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IOProvider.class).toInstance(new FileBasedIOProvider("orders.txt"));
        bind(IOrderTransformer.class).toInstance(new OrderTransformer(new Gson()));
        bind(IOrderStore.class).to(OrderStoreDB.class);
    }
    @Singleton
    @Provides
    Connection connection() {
        Connection connection = null;
        try {
            Properties prop = new Properties();
            FileInputStream input = new FileInputStream("src/config/dbconfig.properties");
            prop.load(input);
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(prop.getProperty("dburl"),prop.getProperty("dbuser"),prop.getProperty("dbpassword"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
