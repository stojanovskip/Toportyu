package com.kerkyra.topapp.model;

/**
 * Created by Andras.Timar on 5/3/2016.
 */
public class Trip {
    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return String.format("ID: "+id+" Name: "+name);
    }
}
