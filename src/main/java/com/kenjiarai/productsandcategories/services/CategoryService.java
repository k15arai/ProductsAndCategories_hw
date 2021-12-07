package com.kenjiarai.productsandcategories.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenjiarai.productsandcategories.models.Category;
import com.kenjiarai.productsandcategories.models.Product;
import com.kenjiarai.productsandcategories.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	// Create
	public Category create(Category item) {
		return this.repository.save(item);
	}
	
	// Read ALL
	public List<Category> all() {
		return this.repository.findAll();
	}
	
	// Read One
	public Category retrieve(Long id) {
		return this.repository.findById(id).get();
	}
	
	// Update
	public Category update(Category item) {
		return this.repository.save(item);
	}
	
	// Delete
	public void delete(Long id) {
		this.repository.deleteById(id);
	}
	
	// ALL Categories for a particular product
	public List<Category> includedCategories(Product product) {
		return this.repository.findAllByProducts(product);
	}
	
	// Categories a product does not belong to
	public List<Category> excludedCategories(Product product) {
		return this.repository.findByProductsNotContains(product);
	}	
}
