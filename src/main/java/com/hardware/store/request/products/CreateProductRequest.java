package com.hardware.store.request.products;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest extends BaseProductRequest {
    private String name;
    private String category;
    private String imageUrl;
}
 