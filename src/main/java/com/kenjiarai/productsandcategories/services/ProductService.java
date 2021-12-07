package com.kenjiarai.productsandcategories.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenjiarai.productsandcategories.models.Category;
import com.kenjiarai.productsandcategories.models.Product;
import com.kenjiarai.productsandcategories.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	// Create
	public Product create(Product item) {
		return this.repository.save(item);
	}
	
	// Read ALL
	public List<Product> all() {
		return this.repository.findAll();
	}
	
	// Read One
	public Product retrieve(Long id) {
		return this.repository.findById(id).get();
	}
	
	// Update
	public Product update(Product item) {
		return this.repository.save(item);
	}
	
	// Delete
	public void delete(Long id) {
		this.repository.deleteById(id);
	}
	
	// ALL Products for a particular category
	public List<Product> includedProducts(Category category) {
		return this.repository.findAllByCategories(category);
	}
	
	// Categories a product does not belong to
	public List<Product> excludedProducts(Category category) {
		return this.repository.findByCategoriesNotContains(category);
	}
}
