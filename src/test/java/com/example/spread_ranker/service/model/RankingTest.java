package com.example.spread_ranker.service.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankingTest {

    @Test
    void shouldDivideElementsIntoGroupsCorrectly() {
        // given
        RankingElement e1 = new RankingElement("KNG_USDT", 1.5);
        RankingElement e2 = new RankingElement("TRX_USDT", 2.5);
        RankingElement e3 = new RankingElement("SOL_USDT", Double.NaN);
        RankingElement e4 = new RankingElement("DOGE_USDT", 0.5);
        RankingElement e5 = new RankingElement("XCH_USDT", 3.0);

        List<RankingElement> elements = List.of(e1, e2, e3, e4, e5);

        // when
        Ranking ranking = new Ranking(elements);

        // then
        // Grupa 1: spread ≤ 2.0
        assertThat(ranking.getGroup1())
                .extracting(RankingElement::getMarket)
                .containsExactly("DOGE_USDT", "KNG_USDT");

        // Grupa 2: spread > 2.0
        assertThat(ranking.getGroup2())
                .extracting(RankingElement::getMarket)
                .containsExactly("TRX_USDT", "XCH_USDT");

        // Grupa 3: spread = NaN
        assertThat(ranking.getGroup3())
                .extracting(RankingElement::getMarket)
                .containsExactly("SOL_USDT");

        assertThat(ranking.getTimestamp()).isNotNull();
    }

    @Test
    void shouldHandleEmptyList() {
        Ranking ranking = new Ranking(List.of());

        assertThat(ranking.getGroup1()).isEmpty();
        assertThat(ranking.getGroup2()).isEmpty();
        assertThat(ranking.getGroup3()).isEmpty();
        assertThat(ranking.getTimestamp()).isNotNull();
    }

    @Test
    void shouldSortGroupsAlphabeticallyByMarket() {
        RankingElement e1 = new RankingElement("ZZZ_USD", 1.0);
        RankingElement e2 = new RankingElement("AAA_USD", 1.0);
        RankingElement e3 = new RankingElement("MMM_USD", Double.NaN);

        Ranking ranking = new Ranking(List.of(e1, e2, e3));

        assertThat(ranking.getGroup1())
                .extracting(RankingElement::getMarket)
                .containsExactly("AAA_USD", "ZZZ_USD");

        assertThat(ranking.getGroup3())
                .extracting(RankingElement::getMarket)
                .containsExactly("MMM_USD");
    }
}