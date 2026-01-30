package com.shopappln.com.controller;

import com.shopappln.com.entity.Product;
import com.shopappln.com.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        return service.getById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
