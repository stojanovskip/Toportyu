package com.company;

import javax.persistence.*;

@Entity
@Table(name="order")
public class Order {

    private long id;
    private double cost;
    private String content;

    @Id
    @Column(name="order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public Order(String content, double cost) {
        this.content = content;
        this.cost = cost;
    }

    @Column(name="order_content")
    String getContent() {
        return content;
    }

    void setContent(String content) {
        this.content = content;
    }

    @Column(name = "order_cost")
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    @Override
    public String toString()
    {
        return String.format("Order: id: "+id+", Content: "+content+", Cost: "+cost);
    }

}