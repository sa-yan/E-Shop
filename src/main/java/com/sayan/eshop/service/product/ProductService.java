package com.sayan.eshop.service.product;

import com.sayan.eshop.model.Product;
import com.sayan.eshop.request.AddProductRequest;

import java.util.List;

public interface ProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Integer id);
    Product updateProduct(AddProductRequest productRequest, Integer id);
    void deleteProduct(Integer id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brandName);
    List<Product> getProductsByCategoryAndBrand(String category, String brandName);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByNameAndBrand(String name, String brandName);
    int countProductsByBrandAndName(String brandName, String name);
}
