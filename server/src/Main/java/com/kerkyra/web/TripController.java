package com.kerkyra.web;

import com.kerkyra.model.Trip;
import com.kerkyra.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Petar.Stojanovski on 5/2/2016.
 */
@RestController
public class TripController {

    @Autowired
    TripService tripService;

    @RequestMapping(value = "/trips", method = RequestMethod.GET)
    public Iterable<Trip> getAllTrips() {
        return tripService.getTrips();
    }

    @RequestMapping(value = "/trips", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertTrip(@RequestBody Trip trip) {
        tripService.insertTrip(trip);
    }
}
