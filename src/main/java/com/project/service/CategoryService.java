package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.model.Category;
import com.project.model.Product;
import com.project.repository.CategoryRepository;
import com.project.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	 @Autowired
	    private CategoryRepository categoryRepository;

	    @Autowired
	    private ProductRepository productRepository;

	    public List<Category> findAllCategories(Pageable pageable) {
	        return categoryRepository.findAll(pageable).getContent();
	    }

	    public Category createCategory(Category category) {
	        return categoryRepository.save(category);
	    }

	    public Category findCategoryById(Long id) {
	        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
	    }

	    public Category updateCategory(Long id, Category categoryDetails) {
	        Category category = findCategoryById(id);
	        category.setName(categoryDetails.getName());
	        return categoryRepository.save(category);
	    }

	    public void deleteCategory(Long id) {
	        categoryRepository.deleteById(id);
	    }

	    public void addProductToCategory(Long categoryId, Product product) {
	        Category category = findCategoryById(categoryId);
	        product.setCategory(category);
	        productRepository.save(product);
	        category.getProducts().add(product);
	        categoryRepository.save(category);
	    }

}
