package com.example.spread_ranker.service.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AskTest {

    @Test
    void shouldCreateAskFromDouble() {
        Ask ask = new Ask(100.5);

        assertThat(ask.getPrice()).isEqualTo(100.5);
    }

    @Test
    void shouldCreateAskFromString() {
        Ask ask = new Ask("150.25");

        assertThat(ask.getPrice()).isEqualTo(150.25);
    }

    @Test
    void bestAskShouldReturnAskWithLowestPrice() {
        Ask a1 = new Ask(100.0);
        Ask a2 = new Ask(105.0);
        Ask a3 = new Ask(102.0);

        Ask best = Ask.bestAsk(List.of(a1, a2, a3));

        assertThat(best).isEqualTo(a1);
    }

    @Test
    void bestAskShouldReturnNaNWhenListIsEmpty() {
        Ask best = Ask.bestAsk(List.of());

        assertThat(best.getPrice()).isNaN();
    }

    @Test
    void bestAskShouldReturnNaNWhenListIsNull() {
        Ask best = Ask.bestAsk(null);

        assertThat(best.getPrice()).isNaN();
    }
}