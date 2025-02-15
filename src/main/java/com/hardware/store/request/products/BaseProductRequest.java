package com.hardware.store.request.products;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseProductRequest {
    private double price;
    private int stockQuantity;
    private String description;
}
