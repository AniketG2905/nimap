package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.model.Category;
import com.project.model.Product;
import com.project.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	@Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = findProductById(id);
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product addCategoryToProduct(Long productId, Long categoryId) {
        Product product = findProductById(productId);
        Category category = categoryService.findCategoryById(categoryId);
        product.setCategory(category);
        return productRepository.save(product);
    }

}
