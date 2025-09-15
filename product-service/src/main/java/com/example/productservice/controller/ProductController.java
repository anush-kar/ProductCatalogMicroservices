package com.example.productservice.controller;

import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {
    private final ProductService svc;

    public ProductController(ProductService svc) {
        this.svc = svc;
    }

    // GET all products
    @GetMapping
    public List<ProductResponse> listProducts(@RequestParam(name = "delay", required = false, defaultValue = "false") boolean delay) {
        return svc.getAllProducts(delay);
    }

    // GET single product by ID
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id,
                                          @RequestParam(name = "delay", required = false, defaultValue = "false") boolean delay) {
        return svc.getProductById(id, delay);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return svc.addProduct(product);
    }

}
