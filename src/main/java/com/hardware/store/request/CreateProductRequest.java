package com.hardware.store.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {

    private String name;
    private String category;
    private double price;
    private int stockQuantity;
    private String description;
    private String imageUrl;
}
