package com.kerkyra.repository;

import com.kerkyra.model.Order;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Andras.Timar on 4/25/2016.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
}
