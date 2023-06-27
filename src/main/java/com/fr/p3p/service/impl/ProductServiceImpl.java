package com.fr.p3p.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.p3p.model.response.MSResponse;
import com.fr.p3p.repository.ProductRepository;
import com.fr.p3p.service.ProductService;
import com.fr.p3p.utils.ErrorCode;
import com.fr.p3p.utils.MSException;
import com.fr.p3p.utils.ResponseHelper;
import com.fr.p3p.model.Product;
import com.fr.p3p.model.request.ProductRequest;

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
	
	public MSResponse addProduct(ProductRequest req) {
		Product prod = null;
		prod = productRepo.findByNameAndCategory(req.getName(), req.getCategory());
		if (prod != null) {
            throw new MSException(ErrorCode.BAD_REQUEST, "Product already exists.");
        } 
		prod = new Product();
		String cat = (req.getCategory().trim()).toUpperCase();
		prod.setCategory(cat);
		prod.setName(req.getName());
		
		productRepo.save(prod);

		return ResponseHelper.createResponse(prod, "Products added successfully.", "Failed to add products.");
	}
}
