package com.example.spread_ranker.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@HttpExchange("https://public.kanga.exchange/api")
public interface ExternalApi {

    @GetExchange("/market/pairs")
    Mono<List<MarketPair>> getMarketPairs();

    @GetExchange("/market/orderbook/{market}")
    Mono<MarketOrderbookResponse> getMarketOrderbook(@PathVariable String market);
}
