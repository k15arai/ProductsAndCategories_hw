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
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	// New Category Page
	@GetMapping("/categories/new")
	public String items(@ModelAttribute("category") Category category, Model model) {
		model.addAttribute("categories", this.categoryService.all());
		return "/categories/category_form.jsp";
	}
	
	// Create New Category
	@PostMapping("categories/new")
	public String add(
			@Valid @ModelAttribute("category") Category category,
			BindingResult result,
			RedirectAttributes redirectAttributes
			) {
		if ( result.hasErrors() ) return "/categories/category_form.jsp";
		
		this.categoryService.create(category);
		
		redirectAttributes.addFlashAttribute("message", "Your Category has been added.");
		
		return "redirect:/categories/new";
	}
	
	// View Category Page
	@GetMapping("categories/{id}")
	public String viewCategory (
			@ModelAttribute("product") Product product,
			@PathVariable Long id, Model model) {
		
		Category category = this.categoryService.retrieve(id);
		
		model.addAttribute("category", category);
		model.addAttribute("includedProducts", productService.includedProducts(category));
		model.addAttribute("excludedProducts", productService.excludedProducts(category));
		return "categories/view_category.jsp";
	}

	// Add Category from View Product Page
	@PostMapping("/category/add/{productId}")
	public String addCategory(
			@PathVariable Long productId,
			@RequestParam(value="category_id") Long id,
			RedirectAttributes redirectAttributes
			) {
		
		Category thisCategory = this.categoryService.retrieve(id);
		Product thisProduct = this.productService.retrieve(productId);
//		System.out.println(thisProduct.getName());
		
		thisCategory.getProducts().add(thisProduct);
		this.categoryService.update(thisCategory);
		
		redirectAttributes.addFlashAttribute("message", "A Category has been added to your Product.");
		
		return String.format("redirect:/products/%d", productId);
		
	}

	// Delete Category
	@GetMapping("/category/delete/{id}")
	public String delete(
			@PathVariable Long id,
			RedirectAttributes redirectAttributes
			) {
		this.categoryService.delete(id);
		
		redirectAttributes.addFlashAttribute("message", "Your Category has been deleted.");
		
		return "redirect:/categories/new";
	}
	
	
	
	
	
}
