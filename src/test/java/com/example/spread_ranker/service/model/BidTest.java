package com.example.spread_ranker.service.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BidTest {

    @Test
    void shouldCreateBidFromDouble() {
        Bid bid = new Bid(100.5);

        assertThat(bid.getPrice()).isEqualTo(100.5);
    }

    @Test
    void shouldCreateBidFromString() {
        Bid bid = new Bid("150.25");

        assertThat(bid.getPrice()).isEqualTo(150.25);
    }

    @Test
    void bestBidShouldReturnBidWithHighestPrice() {
        Bid b1 = new Bid(100.0);
        Bid b2 = new Bid(105.0);
        Bid b3 = new Bid(102.0);

        Bid best = Bid.bestBid(List.of(b1, b2, b3));

        assertThat(best).isEqualTo(b2);
    }

    @Test
    void bestBidShouldReturnNaNWhenListIsEmpty() {
        Bid best = Bid.bestBid(List.of());

        assertThat(best.getPrice()).isNaN();
    }

    @Test
    void bestBidShouldReturnNaNWhenListIsNull() {
        Bid best = Bid.bestBid(null);

        assertThat(best.getPrice()).isNaN();
    }
}