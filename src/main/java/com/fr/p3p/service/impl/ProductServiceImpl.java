package com.fr.p3p.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.p3p.model.response.MSResponse;
import com.fr.p3p.repository.ProductRepository;
import com.fr.p3p.service.ProductService;
import com.fr.p3p.utils.ResponseHelper;
import com.fr.p3p.model.Product;

@Component
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepo;

	public MSResponse getAllProducts() {
		List<Product> products = productRepo.findByIsDeleted(false);
		return ResponseHelper.createResponse(products, "Products retrieved successfully.", "Failed to retrieve products.");
	}
	
	public MSResponse getProductByCategory(String categories) {
		List<Product> products = productRepo.findByCategory(categories);
		return ResponseHelper.createResponse(products, "Products retrieved successfully.", "Failed to retrieve products.");
	}
}
