package com.kerkyra.web;

import com.kerkyra.model.Trip;
import com.kerkyra.service.AuthenticationService;
import com.kerkyra.service.TripService;
import com.mysql.fabric.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andras.Timar on 5/3/2016.
 */
@RestController
public class TripController {
    @Autowired
    TripService tripService;
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/trips", method = RequestMethod.POST)
    public ResponseEntity<?> insertTrip(@CookieValue("sessionID") long sessionID, @RequestBody Trip t) {
        if(authenticationService.getUser(sessionID)!=null){
        tripService.insertTrip(t);
        return new ResponseEntity<Object>(t, HttpStatus.OK);}
        return new ResponseEntity<Object>(null,HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/trips", method = RequestMethod.GET)
    public Iterable<Trip> getTrips() {
        return tripService.getTrips();
    }
}
