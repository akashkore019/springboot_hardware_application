package com.hardware.store.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequest {

    private double price;
    private int stockQuantity;
    private String description;
}


 