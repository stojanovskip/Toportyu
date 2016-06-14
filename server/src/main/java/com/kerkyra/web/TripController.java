package com.kerkyra.web;

import com.kerkyra.model.Trip;
import com.kerkyra.model.TripDto;
import com.kerkyra.service.AuthenticationService;
import com.kerkyra.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Andras.Timar on 5/3/2016.
 */
@RestController
public class TripController {

    private final TripService tripService;
    private final AuthenticationService authenticationService;

    @Autowired
    public TripController(TripService tripService, AuthenticationService authenticationService) {
        this.tripService = tripService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/api/trips", method = RequestMethod.POST)
    public TripDto insertTrip(@CookieValue(value = "sessionId", required = false) Long sessionId, @RequestBody Trip t, HttpServletResponse response) {
        if (sessionId != null && authenticationService.getUser(sessionId) != null) {
            t.setUser(authenticationService.getUser(sessionId));
            tripService.insertTrip(t);
            return new TripDto(t);
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return null;
    }

    @RequestMapping(value = "/api/trips", method = RequestMethod.GET)
    public List<TripDto> getTrips() {
        Iterable<Trip> trips = tripService.getTrips();
        if (trips != null) {
            return StreamSupport.stream(trips.spliterator(), false)
                    .map(TripDto::new).collect(Collectors.toList());
        }
        return null;
    }
}
