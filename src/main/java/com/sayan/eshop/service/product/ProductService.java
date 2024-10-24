package com.sayan.eshop.service.product;

import com.sayan.eshop.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product getProductById(Integer id);
    void updateProduct(Product product, Integer id);
    void deleteProduct(Integer id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brandName);
    List<Product> getProductsByCategoryAndBrand(String category, String brandName);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByNameAndBrand(String name, String brandName);
    int countProductsByBrandAndName(String brandName, String name);
}
