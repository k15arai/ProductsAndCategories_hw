package com.kenjiarai.productsandcategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kenjiarai.productsandcategories.models.Category;
import com.kenjiarai.productsandcategories.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

	List<Product> findAll();
	
	// Retrieves a list of all products for a particular category
    List<Product> findAllByCategories(Category category);
    
    // Retrieves a list of products a particular category does not contain.
    List<Product> findByCategoriesNotContains(Category category);
}
