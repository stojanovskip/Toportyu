package com.kerkyra.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Petar.Stojanovski on 5/2/2016.
 */
@Entity
@Table(name = "trips")
public class Trip {

    @OneToMany
    private List<Order> orders;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private Long getID()
    {
        return id;
    }
    private void setID(Long id)
    {
        this.id = id;
    }
    private String getName()
    {
        return name;
    }
    private void setName(String name)
    {
        this.name = name;
    }
}
