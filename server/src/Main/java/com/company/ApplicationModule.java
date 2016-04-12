package com.company;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Module;

import java.util.Iterator;

/**
 * Created by Andras.Timar on 4/12/2016.
 */
public class ApplicationModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(IOProvider.class).toInstance(new FileBasedIOProvider("orders.txt"));
        bind(IOrderTransformer.class).toInstance(new OrderTransformer(new Gson()));
    }

}
