package com.studenttribe.eurekaservice.productservice.dto.request;

import com.studenttribe.eurekaservice.productservice.enums.Category;
import lombok.Data;

@Data
public class ProductRequestDto {

    private String name;
    private int maxRetailPrice;
    private byte discountPercentage;
    private String brand;
    private Category category;
    private String company;
}
