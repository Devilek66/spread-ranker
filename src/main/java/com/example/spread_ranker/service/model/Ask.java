package com.example.spread_ranker.service.model;

import java.util.Comparator;
import java.util.List;

public class Ask  extends Order {
    public Ask(double price, double volume) {
        super(price, volume);
    }

    public Ask(String price, String amount) {
        super(price, amount);
    }


    public static Ask bestAsk(List<Ask> asks) {
        return asks.stream()
                .min(Comparator.comparingDouble(Ask::getPrice))
                .orElse(new Ask(Double.NaN, Double.NaN));
    }
}
