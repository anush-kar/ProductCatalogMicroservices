package com.example.productservice.config;

import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(ProductRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Product(1L, "Apple iPhone"));
                repo.save(new Product(2L, "Samsung Galaxy"));
                repo.save(new Product(3L, "Google Pixel"));
            }
        };
    }
}
