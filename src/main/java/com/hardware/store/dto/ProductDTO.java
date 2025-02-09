package com.hardware.store.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Long id;
    private String name;
    private String category;
    private double price;
    private int stockQuantity;
    private String description;
    private String imageUrl;
}
