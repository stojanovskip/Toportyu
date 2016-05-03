package com.kerkyra.repository;

import com.kerkyra.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Created by Andras.Timar on 4/25/2016.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query(value = "SELECT o FROM Order o WHERE o.trip.id = ?1")
    List<Order> findOrderById(Long tripID);
}
