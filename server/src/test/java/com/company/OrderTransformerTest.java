package com.company;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bandi on 4/12/2016.
 */

public class OrderTransformerTest {

    OrderTransformer orderTransformer = new OrderTransformer(new Gson());

    @Test
    public void testToString() {
        Order o = new Order();
        o.setContent("teststuff");
        assertEquals("teststuff", orderTransformer.toString(o));
    }

    @Test
    public void testToJson() {

        String json = "{'content':'teststuff'}";
        assertEquals("teststuff", orderTransformer.parseJsonOrder(json).getContent());
    }
}