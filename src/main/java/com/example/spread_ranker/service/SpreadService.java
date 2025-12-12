package com.example.spread_ranker.service;

import com.example.spread_ranker.service.model.Ranking;
import com.example.spread_ranker.service.model.RankingElement;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class SpreadService {
    private Ranking ranking = null;
    private final SpreadRepository spreadRepository;

    public SpreadService(SpreadRepository spreadRepository) {
        this.spreadRepository = spreadRepository;
    }

    public Mono<Ranking> calculate() {
        return spreadRepository.getTickerList()
                .flatMapMany(Flux::fromIterable)
                .flatMap(ticker -> spreadRepository.getMarketOrderbook(ticker)
                        .map(marketOrderbook -> new RankingElement(marketOrderbook.getTickerId(), marketOrderbook.bestBid(), marketOrderbook.bestAsk())))
                .collectList().map(Ranking::new).doOnNext(r -> this.ranking = r);
    }

    public Mono<Ranking> ranking() {
        return Mono.defer(() -> {
            if (ranking == null) {
                return calculate();
            } else {
                return Mono.just(ranking);
            }
        });
    }
}
