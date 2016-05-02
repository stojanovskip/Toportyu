package com.kerkyra.service;

import com.kerkyra.model.Trip;
import com.kerkyra.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Petar.Stojanovski on 5/2/2016.
 */
@Service
public class TripService implements ITripService {

    @Autowired
    TripRepository tripRepository;

    @Override
    public Iterable<Trip> getTrips() {
        return tripRepository.findAll();
    }

    @Override
    public void insertTrip(Trip trip) {
        tripRepository.save(trip);

    }
}
