package com.example.productservice.controller;

import com.example.productservice.dto.ProductResponse;
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

    @GetMapping
    public List<ProductResponse> listProducts(@RequestParam(name = "delay", required = false, defaultValue = "false") boolean delay) {
        return svc.getAllProducts(delay);
    }
}
