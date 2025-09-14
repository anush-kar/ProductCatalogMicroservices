package com.example.productservice.service;

import com.example.productservice.dto.ProductResponse;
import com.example.productservice.dto.RatingDto;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final RatingClient ratingClient;

    public ProductService(ProductRepository productRepository, RatingClient ratingClient) {
        this.productRepository = productRepository;
        this.ratingClient = ratingClient;
    }

    public List<ProductResponse> getAllProducts(boolean forceDelay) {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(p -> {
                    CompletableFuture<RatingDto> future = ratingClient.getRating(p.getId(), forceDelay)
                            .exceptionally(ex -> defaultRating(p.getId()));
                    RatingDto rating = future.join();
                    return new ProductResponse(p.getId(), p.getName(), rating);
                })
                .collect(Collectors.toList());
    }

    private RatingDto defaultRating(Long productId) {
        return new RatingDto(productId, 0.0, 0);
    }
}
