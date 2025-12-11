package com.example.spread_ranker.service.model;

import java.util.Comparator;
import java.util.List;

public class Bid extends Order {
    public Bid(double price, double amount) {
        super(price, amount);
    }

    public Bid(String price, String amount) {
        super(price, amount);
    }

    public static Bid bestBid(List<Bid> bids) {
        return bids.stream()
                .max(Comparator.comparingDouble(Bid::getPrice))
                .orElse(new Bid(Double.NaN, Double.NaN));
    }
}
