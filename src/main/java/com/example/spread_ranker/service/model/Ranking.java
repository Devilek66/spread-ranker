package com.example.spread_ranker.service.model;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Ranking {
    private final String timestamp;
    private final List<RankingElement> group1;
    private final List<RankingElement> group2;
    private final List<RankingElement> group3;

    public String getTimestamp() { return timestamp; }
    public List<RankingElement> getGroup1() { return group1; }
    public List<RankingElement> getGroup2() { return group2; }
    public List<RankingElement> getGroup3() { return group3; }

    public Ranking(List<RankingElement> elements) {
        this.timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.now());

        // Grupa 1 -> spread ≤ 2%
        this.group1 = elements.stream()
                .filter(e -> !Double.isNaN(e.getSpread()) && e.getSpread() <= 2.0)
                .sorted(Comparator.comparing(RankingElement::getMarket))
                .collect(Collectors.toList());

        // Grupa 2 -> spread > 2%
        this.group2 = elements.stream()
                .filter(e -> !Double.isNaN(e.getSpread()) && e.getSpread() > 2.0)
                .sorted(Comparator.comparing(RankingElement::getMarket))
                .collect(Collectors.toList());

        // Grupa 3 -> brak możliwości wyliczenia
        this.group3 = elements.stream()
                .filter(e -> Double.isNaN(e.getSpread()))
                .sorted(Comparator.comparing(RankingElement::getMarket))
                .collect(Collectors.toList());

    }
}
