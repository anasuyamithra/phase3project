package com.fr.p3p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.p3p.model.request.ProductRequest;
import com.fr.p3p.model.response.MSResponse;
import com.fr.p3p.service.ProductService;

@RestController
@RequestMapping("/admin/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping
	public MSResponse getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/category")
	public MSResponse getProductByCategory(@RequestParam(value = "categories", required = false) String categories) {
		return productService.getProductByCategory(categories);
	}
	
	@PostMapping
	public MSResponse addProduct(@RequestBody ProductRequest req) {
		return productService.addProduct(req);
	}
	
	@PutMapping("/{id}")
	public MSResponse updateProduct(@RequestBody ProductRequest req, 
									@PathVariable String id) {
		return productService.updateProduct(req, id);
	}
	
	@DeleteMapping("/{id}")
	public MSResponse deleteProduct(@PathVariable String id) {
		return productService.deleteProduct(id);
	}
}
