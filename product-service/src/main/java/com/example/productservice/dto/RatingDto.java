package com.example.productservice.dto;

public class RatingDto {
    private Long productId;
    private double average;
    private long count;

    public RatingDto() {}

    public RatingDto(Long productId, double average, long count) {
        this.productId = productId;
        this.average = average;
        this.count = count;
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public double getAverage() { return average; }
    public void setAverage(double average) { this.average = average; }
    public long getCount() { return count; }
    public void setCount(long count) { this.count = count; }
}
