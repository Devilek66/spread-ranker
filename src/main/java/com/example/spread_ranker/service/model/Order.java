package com.example.spread_ranker.service.model;

public abstract class Order {
    protected double price;

    public Order(double price) {
        this.price = price;
    }

    public Order(String price) {
        this.price = parseOrNaN(price);
    }

    public double getPrice() {
        return price;
    }

    private double parseOrNaN(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException e) {
            return Double.NaN;
        }
    }
}
