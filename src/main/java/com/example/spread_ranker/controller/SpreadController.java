package com.example.spread_ranker.controller;

import com.example.spread_ranker.service.SpreadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/spread")
public class SpreadController {
    private final SpreadService spreadService;

    public SpreadController(SpreadService spreadService) {
        this.spreadService = spreadService;
    }

    @PostMapping("/calculate")
    public Mono<SpreadDto> calculate() {
        var timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
        return spreadService.calculate().map(ranking -> new SpreadDto(timestamp, RankingDto.from(ranking)));
    }
}
