package com.company;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IOProvider.class).toInstance(new FileBasedIOProvider("orders.txt"));
        bind(IOrderTransformer.class).toInstance(new OrderTransformer(new Gson()));
        bind(IOrderStore.class).to(OrderStoreDB.class);
    }

    @Singleton
    @Provides
    public EntityManager entityManager()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("toportyu");
        EntityManager em = entityManagerFactory.createEntityManager();
        return em;
    }

}
