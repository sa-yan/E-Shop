package com.sayan.eshop.service.category;

import com.sayan.eshop.model.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Integer id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category saveCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategoryById(Integer id);
}
