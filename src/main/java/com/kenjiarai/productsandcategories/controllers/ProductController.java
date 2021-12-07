package com.kenjiarai.productsandcategories.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kenjiarai.productsandcategories.models.Category;
import com.kenjiarai.productsandcategories.models.Product;
import com.kenjiarai.productsandcategories.services.CategoryService;
import com.kenjiarai.productsandcategories.services.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private CategoryService categoryService;
	
	// New Product Page
	@GetMapping("/products/new")
	public String items(@ModelAttribute("product") Product product, Model model ) {
		model.addAttribute("products", this.productService.all());
		return "/products/product_form.jsp";
	}
	
	// Create New Product
	@PostMapping("/products/new")
	public String add(
			@Valid @ModelAttribute("product") Product product,
			BindingResult result,
			RedirectAttributes redirectAttributes
			) {
		if ( result.hasErrors() ) return "/products/product_form.jsp";
		
		this.productService.create(product);
		
		redirectAttributes.addFlashAttribute("message", "Your Product has been added.");
		
		return "redirect:/products/new";
	}
	
	// View Products Page
	@GetMapping("/products/{id}")
	public String viewProduct (
			@ModelAttribute("category") Category category, 
			@PathVariable Long id, Model model) {
		
		Product product = this.productService.retrieve(id);
		
		model.addAttribute("product", product);
		model.addAttribute("includedCategories", categoryService.includedCategories(product));
		model.addAttribute("excludedCategories", categoryService.excludedCategories(product));
		return "products/view_product.jsp";
	}
	
	// Add Product from View Category Page
	@PostMapping("/product/add/{categoryId}")
	public String addProduct(
			@PathVariable Long categoryId,
			@RequestParam(value="product_id") Long id,
			RedirectAttributes redirectAttributes
			) {
		
	    Category thisCategory = this.categoryService.retrieve(categoryId);
	    Product thisProduct = this.productService.retrieve(id);

	    thisProduct.getCategories().add(thisCategory);
	    this.productService.update(thisProduct); 
		
		redirectAttributes.addFlashAttribute("message", "A Product has been added to your Category.");

		return String.format("redirect:/categories/%d", categoryId);
		
	}
	
	// Delete Product
	@GetMapping("/product/delete/{id}")
	public String delete(
			@PathVariable Long id,
			RedirectAttributes redirectAttributes
			) {
		this.productService.delete(id);
		
		redirectAttributes.addFlashAttribute("message", "Your Product has been deleted.");
		
		return "redirect:/products/new";
	}
}
