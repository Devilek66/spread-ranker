package com.example.spread_ranker.service.model;

import java.util.List;

public class MarketOrderbook {
    private final List<Bid> bids;
    private final List<Ask> asks;
    private final String tickerId;

    public MarketOrderbook(List<Bid> bids, List<Ask> asks, String tickerId) {
        this.bids = bids;
        this.asks = asks;
        this.tickerId = tickerId;
    }

    public String getTickerId() {
        return tickerId;
    }

    public Bid bestBid(){
        return Bid.bestBid(this.bids);
    }

    public Ask bestAsk(){
        return Ask.bestAsk(this.asks);
    }
}
