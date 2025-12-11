package com.example.spread_ranker.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public record MarketOrderbookResponse(
        long timestamp,
        @JsonDeserialize(contentUsing = BidAskDeserializer.class)
        List<BidAskResponse> bids,
        @JsonDeserialize(contentUsing = BidAskDeserializer.class)
        List<BidAskResponse> asks,
        @JsonProperty("ticker_id") String tickerId) {

}
