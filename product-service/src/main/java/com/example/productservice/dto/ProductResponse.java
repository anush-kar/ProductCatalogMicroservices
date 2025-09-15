package com.example.productservice.dto;

public class ProductResponse {
    private Long productId;
    private String productName;
    private RatingDto rating;

    public ProductResponse(Long productId, String productName, RatingDto rating) {
        this.productId = productId;
        this.productName = productName;
        this.rating = rating;
    }

    // Getters & setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public RatingDto getRating() {
        return rating;
    }

    public void setRating(RatingDto rating) {
        this.rating = rating;
    }
}
