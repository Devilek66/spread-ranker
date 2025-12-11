package com.example.spread_ranker.service.model;

public abstract class Order {
    protected double price;
    protected double volume;

    public Order(double price, double volume) {
        this.price = price;
        this.volume = volume;
    }

    public Order(String price, String volume) {
        this.price = parseOrNaN(price);
        this.volume = parseOrNaN(volume);
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
