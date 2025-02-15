package com.hardware.store.service;

import com.hardware.store.dto.ProductDTO;
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
        return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductById(Long id) {
        logger.info("Fetching product with ID: {}", id);
        return productRepository.findById(id).map(this::convertToDTO);
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        logger.info("Saving new product: {}", productDTO.getName());
        Product product = convertToEntity(productDTO);
        return convertToDTO(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        logger.warn("Deleting product with ID: {}", id);
        productRepository.deleteById(id);
    }

    private ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .build();
    }

    private Product convertToEntity(ProductDTO dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .stockQuantity(dto.getStockQuantity())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .build();
    }
}
