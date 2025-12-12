package com.example.spread_ranker.service.model;

import java.util.Comparator;
import java.util.List;

public class Ask  extends Order {
    public Ask(double price) {
        super(price);
    }

    public Ask(String price) {
        super(price);
    }


    public static Ask bestAsk(List<Ask> asks) {
        if(asks == null || asks.isEmpty())
        {
            return new Ask();
        }
        return asks.stream()
                .min(Comparator.comparingDouble(Ask::getPrice))
                .orElse(new Ask());
    }

    protected Ask()
    {
        super(Double.NaN);
    }
}
