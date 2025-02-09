package com.hardware.store.controller;

import com.hardware.store.dto.ProductDTO;
import com.hardware.store.model.Product;
import com.hardware.store.request.CreateProductRequest;
import com.hardware.store.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Product> getProducts() {
        logger.info("Fetching all products");
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody CreateProductRequest request) {
        Product product = new Product(
                null,
                request.getName(),
                request.getCategory(),
                request.getPrice(),
                request.getStockQuantity(),
                request.getDescription(),
                request.getImageUrl()
        );
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        logger.warn("Product deleted with ID: {}", id);
        return "Product deleted successfully";
    }
}
