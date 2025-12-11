package com.example.spread_ranker.controller;

import com.example.spread_ranker.service.model.RankingElement;

import java.util.Locale;

public record RankingElementDto(String market, String spread) {
    public static RankingElementDto from(RankingElement element)
    {
        String spread = "N/A";
        if(!Double.isNaN(element.getSpread()))
        {
            spread = String.format(Locale.US, "%.2f", element.getSpread());
        }
        return new RankingElementDto(element.getMarket(), spread);
    }
}
