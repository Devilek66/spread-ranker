package com.example.spread_ranker.client;

import com.example.spread_ranker.service.model.MarketOrderbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.*;

class KangaClientTest {

    ExternalApi externalApi;
    KangaClient kangaClient;

    @BeforeEach
    void setup() {
        externalApi = mock(ExternalApi.class);
        kangaClient = new KangaClient(externalApi);
    }

    @Test
    void getTickerListShouldMapMarketPairsToStrings() {
        // given
        MarketPair pair1 = mock(MarketPair.class);
        MarketPair pair2 = mock(MarketPair.class);
        when(pair1.tickerId()).thenReturn("DAO_USDT");
        when(pair2.tickerId()).thenReturn("JTO_USDT");

        when(externalApi.getMarketPairs()).thenReturn(Mono.just(List.of(pair1, pair2)));

        // when
        Mono<List<String>> result = kangaClient.getTickerList();

        // then
        StepVerifier.create(result)
                .expectNext(List.of("DAO_USDT", "JTO_USDT"))
                .verifyComplete();
    }

    @Test
    void getMarketOrderbookShouldMapResponseToMarketOrderbook() {
        // given
        MarketOrderbookResponse response = mock(MarketOrderbookResponse.class);
        BidAskResponse bidResp = mock(BidAskResponse.class);
        BidAskResponse askResp = mock(BidAskResponse.class);

        when(bidResp.price()).thenReturn("100.0");
        when(askResp.price()).thenReturn("101.0");

        when(response.bids()).thenReturn(List.of(bidResp));
        when(response.asks()).thenReturn(List.of(askResp));
        when(response.tickerId()).thenReturn("DAO_USDT");

        when(externalApi.getMarketOrderbook("DAO_USDT")).thenReturn(Mono.just(response));

        // when
        Mono<MarketOrderbook> result = kangaClient.getMarketOrderbook("DAO_USDT");

        // then
        StepVerifier.create(result)
                .assertNext(marketOrderbook -> {
                    assert marketOrderbook.getTickerId().equals("DAO_USDT");
                    assert marketOrderbook.getBids().size() == 1;
                    assert marketOrderbook.getBids().getFirst().getPrice() == 100.0;
                    assert marketOrderbook.getAsks().size() == 1;
                    assert marketOrderbook.getAsks().getFirst().getPrice() == 101.0;
                })
                .verifyComplete();
    }
}