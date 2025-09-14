package com.example.ratingservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    private Long productId;

    @Column(nullable = false)
    private double average;

    @Column(nullable = false)
    private long count;

    public Rating() {}

    public Rating(Long productId, double average, long count) {
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
