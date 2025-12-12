package com.example.spread_ranker.service;

import com.example.spread_ranker.service.model.MarketOrderbook;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SpreadRepository {
    Mono<List<String>> getTickerList();
    Mono<MarketOrderbook> getMarketOrderbook(String market);
}
