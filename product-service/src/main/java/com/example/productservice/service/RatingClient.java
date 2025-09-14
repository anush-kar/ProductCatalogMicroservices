package com.example.productservice.service;

import com.example.productservice.dto.RatingDto;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Service
public class RatingClient {

    private final WebClient webClient;

    public RatingClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://rating-service:8081").build();
    }

    @TimeLimiter(name = "ratingService", fallbackMethod = "fallbackGetRating")
    public CompletableFuture<RatingDto> getRating(Long productId, boolean delay) {
        // Run async manually
        return CompletableFuture.supplyAsync(() ->
            webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/ratings/{productId}")
                        .queryParam("delay", delay)
                        .build(productId))
                .retrieve()
                .bodyToMono(RatingDto.class)
                .block()   // blocking here, but wrapped in async thread
        );
    }

    // ✅ Fallback signature must match params + Throwable
    private CompletableFuture<RatingDto> fallbackGetRating(Long productId, boolean delay, Throwable t) {
        return CompletableFuture.completedFuture(new RatingDto(productId, 0.0, 0));
    }
}
