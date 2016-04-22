package com.kerkyra.datahandling;


import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    private Long id;
    private int cost;
    private String content;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "cost")
    public int getCost() {return cost;}
    public void setCost(int cost) {
        this.cost = cost;
    }

}