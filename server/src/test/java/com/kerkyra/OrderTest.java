package com.kerkyra;

import com.kerkyra.datahandling.Order;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Andras.Timar on 4/12/2016.
 */
public class OrderTest {
    @Test
    public void setAndGetContent() throws Exception {
        Order o = new Order();
        o.setCost(10);
        o.setId((long) 1);
        o.setContent("test");
        assertEquals("test", o.getContent());
        assertEquals(10,o.getCost());
        assertEquals(Long.valueOf(1),o.getId());
    }

}