package com.kerkyra.repository;

import com.kerkyra.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Created by Andras.Timar on 4/25/2016.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByTrip_id(Long trip_id);
}
