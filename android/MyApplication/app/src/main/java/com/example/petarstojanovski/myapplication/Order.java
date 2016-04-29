package com.example.petarstojanovski.myapplication;

/**
 * Created by Petar.Stojanovski on 4/29/2016.
 */
public class Order {
    private String content;

    private int cost;

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
    public Order(String content, int cost)
    {
        this.content = content;
        this.cost = cost;
    }
    public String toStringConv()
    {
        return String.format(getContent() + " " + getCost());
    }
}
