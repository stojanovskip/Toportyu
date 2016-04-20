package com.company;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
import javax.persistence.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
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
    public EntityManager run()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("toportyu");
        EntityManager em = entityManagerFactory.createEntityManager();
        return em;
    }

}
