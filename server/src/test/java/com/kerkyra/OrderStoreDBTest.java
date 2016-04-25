package com.kerkyra;

import com.kerkyra.datahandling.Order;
import com.kerkyra.datahandling.OrderStoreDB;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Petar.Stojanovski on 4/25/2016.
 */
public class OrderStoreDBTest {

    private EntityManager entityManager = null;
    private EntityTransaction tx;
    private OrderStoreDB orderStoreDB;
    private TypedQuery<Order> query;

    @Before
    public void setUpClass() throws Exception {
        if (entityManager == null) {
            query= mock(TypedQuery.class);
            tx = mock(EntityTransaction.class);
            List<Order> orders = new ArrayList<>();
            orders.add(new Order());
            when(query.getResultList()).thenReturn(orders);
            entityManager = mock(EntityManager.class);
            when(entityManager.getTransaction()).thenReturn(tx);
            when(entityManager.createQuery("SELECT o FROM Order o", Order.class)).thenReturn(query);
            orderStoreDB = new OrderStoreDB(entityManager);
        }
    }


    @Test
    public void testSaveOrder() throws IOException {


        Order order = new Order();
        orderStoreDB.saveOrder(order);
        verify(entityManager).persist(order);
        verify(tx).begin();
        verify(tx).commit();
    }

    @Test
    public void testGetOrder() throws IOException
    {
        orderStoreDB.getOrders();
        verify(tx).begin();
        verify(tx).commit();
        verify(query).getResultList();

    }


  /*  public List<Order> getOrders() throws IOException {
        List<Order> orders;
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o", Order.class);
        orders = query.getResultList();
        tx.commit();

        return orders;
    }
*/
}