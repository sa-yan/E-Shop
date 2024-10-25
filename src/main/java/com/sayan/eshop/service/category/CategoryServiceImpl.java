package com.sayan.eshop.service.category;

import com.sayan.eshop.exception.ResourceNotFoundException;
import com.sayan.eshop.model.Category;
import com.sayan.eshop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category Not Found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository
                .findById(id)
                .ifPresentOrElse(categoryRepository::delete, ()->{
                    throw new ResourceNotFoundException("Category Not Found");
                });
    }
}
