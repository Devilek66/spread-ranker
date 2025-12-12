package com.example.spread_ranker.controller;

import com.example.spread_ranker.service.model.RankingElement;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankingElementDtoTest {

    @Test
    void shouldConvertElementWithValidSpread() {
        // given
        RankingElement element = new RankingElement("DOGE_USDT", 1.2345);

        // when
        RankingElementDto dto = RankingElementDto.from(element);

        // then
        assertThat(dto.market()).isEqualTo("DOGE_USDT");
        assertThat(dto.spread()).isEqualTo("1.23");
    }

    @Test
    void shouldConvertElementWithNaNSpread() {
        // given
        RankingElement element = new RankingElement("NAKA_USDT", Double.NaN);

        // when
        RankingElementDto dto = RankingElementDto.from(element);

        // then
        assertThat(dto.market()).isEqualTo("NAKA_USDT");
        assertThat(dto.spread()).isEqualTo("N/A");
    }
}