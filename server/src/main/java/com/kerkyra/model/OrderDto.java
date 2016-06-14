package com.kerkyra.model;

/**
 * Created by Andras.Timar on 6/10/2016.
 */
public class OrderDto {
    public final TripDto trip;
    public final UserDto user;
    public final int cost;
    public final String content;

    public OrderDto(Order order) {
        this.trip = new TripDto(order.getTrip());
        this.content = order.getContent();
        this.cost = order.getCost();
        this.user = new UserDto(order.getUser());
    }

}
