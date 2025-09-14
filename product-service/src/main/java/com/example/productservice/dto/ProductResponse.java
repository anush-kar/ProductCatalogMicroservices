package com.example.productservice.dto;

public class ProductResponse {
    private Long id;
    private String name;
    private RatingDto rating;

    public ProductResponse() {}
    public ProductResponse(Long id, String name, RatingDto rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public RatingDto getRating() { return rating; }
    public void setRating(RatingDto rating) { this.rating = rating; }
}
