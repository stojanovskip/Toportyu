package com.kerkyra.model;

/**
 * Created by Andras.Timar on 4/25/2016.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long id;
    @Column(name = "cost")
    private int cost;
    @Column(name = "content")
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


    public int getCost() {return cost;}
    public void setCost(int cost) {
        this.cost = cost;
    }

}