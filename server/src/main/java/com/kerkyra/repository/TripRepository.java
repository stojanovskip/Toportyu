package com.kerkyra.repository;

import com.kerkyra.model.Trip;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Andras.Timar on 5/2/2016.
 */
public interface TripRepository extends CrudRepository<Trip, Long> {
}
