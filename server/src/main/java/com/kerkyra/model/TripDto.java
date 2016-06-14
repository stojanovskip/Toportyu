package com.kerkyra.model;

/**
 * Created by Andras.Timar on 6/10/2016.
 */
public class TripDto {
    public final UserDto user;
    public final Long id;
    public final String name;

    public TripDto(Trip trip) {
        this.user = new UserDto(trip.getUser());
        this.id = trip.getId();
        this.name = trip.getName();
    }
}
