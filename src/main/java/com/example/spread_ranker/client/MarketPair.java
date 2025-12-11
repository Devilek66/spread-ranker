package com.example.spread_ranker.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MarketPair(@JsonProperty("ticker_id") String tickerId, String base, String target) {
}
