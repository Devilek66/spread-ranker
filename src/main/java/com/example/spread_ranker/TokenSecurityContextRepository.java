package com.example.spread_ranker;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class TokenSecurityContextRepository implements ServerSecurityContextRepository {

    private final String expectedToken;

    public TokenSecurityContextRepository(String expectedToken) {
        this.expectedToken = expectedToken;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, org.springframework.security.core.context.SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<org.springframework.security.core.context.SecurityContext> load(ServerWebExchange exchange) {
        var authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.equals("Bearer " + expectedToken)) {
            var auth = new UsernamePasswordAuthenticationToken("user", null, null);
            return Mono.just(new SecurityContextImpl(auth));
        }

        return Mono.empty();
    }
}
