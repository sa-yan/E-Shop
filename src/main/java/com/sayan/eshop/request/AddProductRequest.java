package com.sayan.eshop.request;

import com.sayan.eshop.model.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private Integer id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;
    private Category category;
}
