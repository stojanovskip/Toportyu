package com.kerkyra;

import com.google.gson.Gson;
import com.kerkyra.datahandling.Order;
import com.kerkyra.datahandling.OrderTransformer;
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
    public void fromString() {
        String order = "testorder";
        Order o = orderTransformer.parseStringOrder(order);
        assertEquals("testorder", o.getContent());
    }

    @Test
    public void testFromJson() {
        String json = "{'content':'teststuff'}";
        assertEquals("teststuff", orderTransformer.parseJsonOrder(json).getContent());
    }
    @Test
    public void testToJson()
    {
        Order o = new Order();
        o.setContent("testcontent");
        o.setCost(100);
        o.setId((long) 1);

        assertEquals("{\"id\":1,\"cost\":100.0,\"content\":\"testcontent\"}",orderTransformer.toJson(o));
    }
}