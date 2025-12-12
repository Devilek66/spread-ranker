package com.example.spread_ranker.service.model;

public class RankingElement {
    private final String market;
    private final double spread;

    public RankingElement(String market, Bid bestBid, Ask bestAsk)
    {
        this.market = market;
        this.spread = calculateSpread(bestBid, bestAsk);
    }

    public RankingElement(String market, double spread)
    {
        this.market = market;
        this.spread = spread;
    }

    public String getMarket() {
        return market;
    }

    public double getSpread() {
        return spread;
    }

    protected double calculateSpread(Bid bid, Ask ask){
        if(isNaN(bid, ask))
        {
            return Double.NaN;
        }
        double bidPrice = bid.getPrice();
        double askPrice = ask.getPrice();

        double spread = askPrice - bidPrice;
        double avg = 0.5 * (askPrice + bidPrice);

        return (spread / avg) * 100;
    }

    private boolean isNaN(Bid bid, Ask ask)
    {
        return  bid == null ||
                ask == null ||
                bid.getPrice() == 0 ||
                ask.getPrice() == 0 ||
                Double.isNaN(bid.getPrice()) ||
                Double.isNaN(ask.getPrice());
    }
}
