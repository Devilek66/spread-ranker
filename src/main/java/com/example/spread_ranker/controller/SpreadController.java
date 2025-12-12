package com.example.spread_ranker.controller;

import com.example.spread_ranker.service.SpreadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/spread")
public class SpreadController {
    private final SpreadService spreadService;

    public SpreadController(SpreadService spreadService) {
        this.spreadService = spreadService;
    }

    @PostMapping("/calculate")
    public Mono<SpreadDto> calculate() {
        return spreadService.calculate().map(ranking -> new SpreadDto(ranking.getTimestamp(), RankingDto.from(ranking)));
    }

    @GetMapping("/ranking")
    public Mono<SpreadDto> ranking(){
        return spreadService.ranking().map(ranking -> new SpreadDto(ranking.getTimestamp(), RankingDto.from(ranking)));
    }
}
