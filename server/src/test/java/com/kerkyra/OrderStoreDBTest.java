package com.kerkyra;

import com.kerkyra.datahandling.Order;
import com.kerkyra.datahandling.OrderStoreDB;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertNotSame;

/**
 * Created by Petar.Stojanovski on 4/25/2016.
 */
public class OrderStoreDBTest {

    private static EntityManager em = null;

    @BeforeClass
    public static void setUpClass() throws Exception {
        if (em == null) {

            em = Persistence.createEntityManagerFactory("toportyu").createEntityManager();
        }
    }

    @Test
    public void testSaveOrder() throws IOException {
        OrderStoreDB orderStore = new OrderStoreDB(em);
        Order ord = new Order();
        ord.setContent("test");
        ord.setCost(100);
        orderStore.saveOrder(ord);
        Order ord2 = new Order();
        ord2.setContent("test");
        ord2.setCost(123);

        orderStore.saveOrder(ord2);
        long newid = ord.getId();
        List<Order> orders = orderStore.getOrders();
        Query query = em.createQuery("SELECT o FROM Order o where o.id=1");
        assertNotSame(query.getSingleResult(),ord);
    }
}
