package com.hardware.store.service;

import com.hardware.store.model.Product;
import com.hardware.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        logger.info("Fetching all products...");
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        logger.info("Fetching product with ID: {}", id);
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        logger.info("Saving new product: {}", product.getName());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        logger.warn("Deleting product with ID: {}", id);
        productRepository.deleteById(id);
    }
}
