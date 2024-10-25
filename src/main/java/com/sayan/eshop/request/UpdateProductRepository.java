package com.sayan.eshop.request;

import com.sayan.eshop.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRepository {
    private Integer id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;
    private Category category;
}
