package com.hardware.store.service;

import com.hardware.store.dto.ProductDTO;
import com.hardware.store.mapper.ProductMapper;
import com.hardware.store.model.Product;
import com.hardware.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        logger.info("Fetching all products...");
        return productRepository.findAll().stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductById(Long id) {
        logger.info("Fetching product with ID: {}", id);
        return productRepository.findById(id).map(ProductMapper::toDTO);
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        logger.info("Saving new product: {}", productDTO.getName());
        Product product = ProductMapper.toEntity(productDTO);
        return ProductMapper.toDTO(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        logger.warn("Deleting product with ID: {}", id);
        productRepository.deleteById(id);
    }
}
