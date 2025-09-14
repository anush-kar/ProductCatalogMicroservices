package com.example.ratingservice.controller;

import com.example.ratingservice.model.Rating;
import com.example.ratingservice.repository.RatingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingRepository ratingRepository;

    public RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Rating> getRating(@PathVariable Long productId,
                                            @RequestParam(name = "delay", required = false, defaultValue = "false") boolean delay) throws InterruptedException {
        if (delay) {
            // simulate latency for timeout demo
            Thread.sleep(5000);
        }

        return ratingRepository.findById(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.ok(new Rating(productId, 0.0, 0)));
    }
}
