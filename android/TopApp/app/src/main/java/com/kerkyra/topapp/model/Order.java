package com.kerkyra.topapp.model;

/**
 * Created by Andras.Timar on 4/29/2016.
 */
public class Order {
    private Long id;
    private String content;
    private int cost;
    private Trip trip;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return String.format(content+"  cost: "+cost);
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
