package com.example.spread_ranker.client;

import com.example.spread_ranker.service.SpreadRepository;
import com.example.spread_ranker.service.model.Ask;
import com.example.spread_ranker.service.model.Bid;
import com.example.spread_ranker.service.model.MarketOrderbook;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Component
public class KangaClient implements SpreadRepository {

    private final ExternalApi externalApi;

    public KangaClient(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public Mono<List<String>> getTickerList() {
        return externalApi.getMarketPairs().map(pairs -> pairs.stream().map(MarketPair::tickerId).toList()).retryWhen(Retry.backoff(3, Duration.ofSeconds(1))
                .filter(throwable -> throwable instanceof WebClientRequestException)
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
                        retrySignal.failure()
                ));
    }

    public Mono<MarketOrderbook> getMarketOrderbook(String market) {
        return externalApi.getMarketOrderbook(market).map(this::from).retryWhen(Retry.backoff(3, Duration.ofMillis(500))
                .filter(throwable -> throwable instanceof WebClientRequestException)
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
                        retrySignal.failure()
                ));
    }

    protected MarketOrderbook from(MarketOrderbookResponse response)
    {
        var bids = response.bids().stream().map(bid -> new Bid(bid.price())).toList();
        var asks = response.asks().stream().map(ask -> new Ask(ask.price())).toList();
        var tickerId = response.tickerId();
        return new MarketOrderbook(bids, asks, tickerId);
    }
}
