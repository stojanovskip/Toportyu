package com.company;

class Order {
    private int cost;
    private String content;

    String getContent() {
        return content;
    }

    void setContent(String content) {
        this.content = content;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}