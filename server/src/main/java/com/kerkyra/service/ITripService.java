package com.kerkyra.service;

import com.kerkyra.model.Trip;

/**
 * Created by Andras.Timar on 5/2/2016.
 */
public interface ITripService {
    void insertTrip(Trip t);

    Iterable<Trip> getTrips();
}
