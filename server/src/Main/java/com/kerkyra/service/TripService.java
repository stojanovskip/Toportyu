package com.kerkyra.service;

import com.kerkyra.model.Trip;
import com.kerkyra.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andras.Timar on 5/2/2016.
 */
@Service
public class TripService implements ITripService {

    TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public void insertTrip(Trip t) {
        tripRepository.save(t);
    }

    @Override
    public Iterable<Trip> getTrips() {
        return tripRepository.findAll();
    }
}
