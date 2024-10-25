package com.sayan.eshop.service.product;

import com.sayan.eshop.exception.ProductNotFoundException;
import com.sayan.eshop.model.Category;
import com.sayan.eshop.model.Product;
import com.sayan.eshop.repository.CategoryRepository;
import com.sayan.eshop.repository.ProductRepository;
import com.sayan.eshop.request.AddProductRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        // check if the category is found in DB
        // If yes, set it as the new product category
        // If no, create new category and save it
        // Then set as new product category

        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(()->{
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
       return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest productRequest, Category category) {
        return new Product(
                productRequest.getName(),
                productRequest.getBrand(),
                productRequest.getPrice(),
                productRequest.getInventory(),
                category
        );
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository
                .findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product Not Found"));
    }

    @Override
    public Product updateProduct(AddProductRequest productRequest, Integer id) {
        return productRepository.findById(id)
                .map(existingProduct -> updateExistingProduct(existingProduct, productRequest))
                .map(productRepository::save)
                .orElseThrow(()-> new ProductNotFoundException("Product Not Found"));
    }

    private Product updateExistingProduct(Product existingProduct, AddProductRequest productRequest) {
        existingProduct.setName(productRequest.getName());
        existingProduct.setBrand(productRequest.getBrand());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setInventory(productRequest.getInventory());
        existingProduct.setDescription(productRequest.getDescription());

        Category category = categoryRepository.findByName(productRequest.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository
                .findById(id)
                .ifPresentOrElse(productRepository::delete, ()->{throw new ProductNotFoundException("Product Not Found");});
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brandName) {
        return productRepository.findByBrand(brandName);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brandName) {
        return productRepository.findByCategoryNameAndBrand(category, brandName);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByNameAndBrand(String name, String brandName) {
        return productRepository.findByBrandAndName(brandName, name);
    }

    @Override
    public int countProductsByBrandAndName(String brandName, String name) {
        return productRepository.countByBrandAndName(brandName, name);
    }
}
