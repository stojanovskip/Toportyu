package com.kerkyra.service;

import com.kerkyra.model.Trip;
import com.kerkyra.repository.TripRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Andras.Timar on 5/3/2016.
 */
public class TripServiceTest {
    @Mock
    TripRepository tripRepository;

    TripService tripService;

    List<Trip> trips;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        tripService = new TripService(tripRepository);
        trips = new ArrayList<>();
        Trip t1 = new Trip();
        t1.setName("test");
        Trip t2 = new Trip();
        t1.setName("test2");
        trips.add(new Trip());
        trips.add(new Trip());
        when(tripRepository.findAll()).thenReturn(trips);
    }

    @Test
    public void insertTrip() throws Exception {
        Trip trip = new Trip();
        tripService.insertTrip(trip);
        verify(tripRepository).save(trip);
    }

    @Test
    public void getTrips() throws Exception {
        List<Trip> tripsBack = (List<Trip>) tripService.getTrips();
        Assert.assertEquals(2, tripsBack.size());
        Assert.assertEquals(trips.get(0).getName(), tripsBack.get(0).getName());
        Assert.assertEquals(trips.get(1).getName(), tripsBack.get(1).getName());

    }

}