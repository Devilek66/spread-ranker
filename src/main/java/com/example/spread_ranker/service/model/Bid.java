package com.example.spread_ranker.service.model;

import java.util.Comparator;
import java.util.List;

public class Bid extends Order {
    public Bid(double price) {
        super(price);
    }

    public Bid(String price) {
        super(price);
    }

    public static Bid bestBid(List<Bid> bids) {
        if(bids == null || bids.isEmpty())
        {
            return new Bid();
        }
        return bids.stream()
                .max(Comparator.comparingDouble(Bid::getPrice))
                .orElse(new Bid());
    }

    protected Bid()
    {
        super(Double.NaN);
    }
}
