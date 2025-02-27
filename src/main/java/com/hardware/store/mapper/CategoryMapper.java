package com.hardware.store.mapper;

import com.hardware.store.dto.CategoryDTO;
import com.hardware.store.model.Category;

public class CategoryMapper {
    
    public static CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
