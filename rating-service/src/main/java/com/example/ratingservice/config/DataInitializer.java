package com.example.ratingservice.config;

import com.example.ratingservice.model.Rating;
import com.example.ratingservice.repository.RatingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(RatingRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Rating(1L, 4.5, 10));
                repo.save(new Rating(2L, 3.8, 4));
                repo.save(new Rating(3L, 5.0, 20));
            }
        };
    }
}
