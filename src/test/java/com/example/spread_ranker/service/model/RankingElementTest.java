package com.example.spread_ranker.service.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.*;

class RankingElementTest {

    @Test
    void shouldCalculateSpreadCorrectly() {
        Bid bid = new Bid(100, 1);
        Ask ask = new Ask(102, 1);

        RankingElement element = new RankingElement("BTC_USD", bid, ask);

        assertThat(element.getSpread()).isCloseTo(1.9801980198019802, within(0.0001));
        assertThat(element.getMarket()).isEqualTo("BTC_USD");
    }

    @Test
    void shouldReturnNaNIfBidIsNull() {
        Ask ask = new Ask(102, 1);
        RankingElement element = new RankingElement("BTC_USD", null, ask);

        assertThat(element.getSpread()).isNaN();
    }

    @Test
    void shouldReturnNaNIfAskIsNull() {
        Bid bid = new Bid(100, 1);
        RankingElement element = new RankingElement("BTC_USD", bid, null);

        assertThat(element.getSpread()).isNaN();
    }

    @Test
    void shouldReturnNaNIfBidOrAskIsZero() {
        Bid bid = new Bid(0, 1);
        Ask ask = new Ask(102, 1);
        RankingElement element = new RankingElement("BTC_USD", bid, ask);

        assertThat(element.getSpread()).isNaN();
    }
}