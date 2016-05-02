package com.kerkyra.model;

/**
 * Created by Andras.Timar on 4/25/2016.
 */

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {


    @ManyToOne
    @Cascade(CascadeType.PERSIST)
    private Trip trip;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int cost;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


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

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}