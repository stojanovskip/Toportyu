package com.kerkyra.web;

import com.kerkyra.model.Trip;
import com.kerkyra.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Andras.Timar on 5/3/2016.
 */
@RestController
public class TripController {
    @Autowired
    TripService tripService;

    @RequestMapping(value = "/api/trips", method = RequestMethod.POST)
    public Trip insertTrip(@RequestBody Trip t) {
        tripService.insertTrip(t);
        return t;
    }

    @RequestMapping(value = "/api/trips", method = RequestMethod.GET)
    public Iterable<Trip> getTrips() {
        return tripService.getTrips();
    }
}
