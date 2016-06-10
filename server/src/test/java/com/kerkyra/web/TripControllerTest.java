package com.kerkyra.web;

import com.kerkyra.model.Trip;
import com.kerkyra.model.TripDto;
import com.kerkyra.model.User;
import com.kerkyra.service.AuthenticationService;
import com.kerkyra.service.TripService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Andras.Timar on 6/10/2016.
 */
public class TripControllerTest {

    @Mock
    AuthenticationService authenticationService;
    @Mock
    TripService tripService;
    TripController tripController;

    User user;
    private Trip trip;
    @Spy
    MockHttpServletResponse httpResponse;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        trip = new Trip();
        when(authenticationService.getUser((long) 100)).thenReturn(user);
        tripController = new TripController(tripService, authenticationService);
    }

    @Test
    public void insert_Should_Return_Null_If_SessionIdIsNull_andNotCallInsertOrder() {
        Long sessionId = null;

        assertNull(tripController.insertTrip(sessionId, trip, httpResponse));
        verify(tripService, times(0)).insertTrip(trip);
        assertEquals(httpResponse.getStatus(), HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    public void insert_should_Return_Trip_If_SessionIdIsValid_andCallInsertOrder() {
        Long sessionId = Long.valueOf(100);
        TripDto tripRespone = tripController.insertTrip(sessionId,trip,httpResponse);
        assertNotNull(tripRespone);
        verify(tripService, times(1)).insertTrip(trip);
        assertNotEquals(httpResponse.getStatus(), HttpServletResponse.SC_UNAUTHORIZED);
        assertEquals(user.getUsername(),tripRespone.name);
    }
    @Test
    public void insert_Should_Return_CorrectTripDto(){
        Long sessionId = Long.valueOf(100);
        TripDto tripRespone = tripController.insertTrip(sessionId,trip,httpResponse);
        assertEquals(user.getUsername(),tripRespone.name);

    }
}