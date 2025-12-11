package com.example.spread_ranker.controller;

import com.example.spread_ranker.service.model.Ranking;

import java.util.List;

public record RankingDto(List<RankingElementDto> group1, List<RankingElementDto> group2, List<RankingElementDto> group3) {
    public static RankingDto from(Ranking ranking)
    {
        var group1 = ranking.getGroup1().stream().map(RankingElementDto::from).toList();
        var group2 = ranking.getGroup2().stream().map(RankingElementDto::from).toList();
        var group3 = ranking.getGroup3().stream().map(RankingElementDto::from).toList();
        return new RankingDto(group1, group2, group3);
    }
}
