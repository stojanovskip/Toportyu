package com.kerkyra.service;


import com.kerkyra.model.Trip;

/**
 * Created by Petar.Stojanovski on 5/2/2016.
 */
public interface ITripService {
    Iterable<Trip> getTrips();

    void insertTrip(Trip trip);
}
