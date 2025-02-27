package com.hardware.store.service;

import com.hardware.store.dto.CategoryDTO;
import com.hardware.store.exception.ResourceNotFoundException;
import com.hardware.store.exception.ConflictException;
import com.hardware.store.mapper.CategoryMapper;
import com.hardware.store.model.Category;
import com.hardware.store.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        logger.info("Fetching all categories...");
        List<CategoryDTO> categories = categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("No categories found.");
        }
        return categories;
    }
    

    public CategoryDTO getCategoryById(Long id) {
        logger.info("Fetching category with ID: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
        return CategoryMapper.toDTO(category);
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        categoryRepository.findByName(categoryDTO.getName()).ifPresent(existing -> {
            throw new ConflictException("Category with name '" + categoryDTO.getName() + "' already exists.");
        });
        Category category = CategoryMapper.toEntity(categoryDTO);
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }
    

    public void deleteCategory(Long id) {
        logger.warn("Deleting category with ID: {}", id);
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
