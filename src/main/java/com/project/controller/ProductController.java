package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Product;
import com.project.service.CategoryService;
import com.project.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	 @Autowired
	    private ProductService productService;

	    @Autowired
	    private CategoryService categoryService;

	    @GetMapping
	    public List<Product> getAllProducts(Pageable pageable) {
	        return productService.findAllProducts(pageable);
	    }

	    @PostMapping
	    public Product createProduct(@RequestBody Product product) {
	        return productService.createProduct(product);
	    }

	    @GetMapping("/{id}")
	    public Product getProductById(@PathVariable Long id) {
	        return productService.findProductById(id);
	    }

	    @PutMapping("/{id}")
	    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
	        return productService.updateProduct(id, productDetails);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	    }

	    @PostMapping("/{id}/category")
	    public Product addCategoryToProduct(@PathVariable Long id, @RequestBody Long categoryId) {
	        return productService.addCategoryToProduct(id, categoryId);
	    }

}
