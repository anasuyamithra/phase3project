package com.fr.p3p.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.p3p.model.response.MSResponse;
import com.fr.p3p.repository.ProductRepository;
import com.fr.p3p.service.ProductService;
import com.fr.p3p.utils.ErrorCode;
import com.fr.p3p.utils.MSException;
import com.fr.p3p.utils.ResponseHelper;
import com.fr.p3p.model.Product;
import com.fr.p3p.model.request.ProductRequest;

@Service
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
		String cat = (req.getCategory().trim()).toUpperCase();
		prod = productRepo.findByNameAndCategoryAndIsDeleted(req.getName(), cat, false);
		if (prod != null) {
            throw new MSException(ErrorCode.BAD_REQUEST, "Product already exists.");
        } 
		prod = new Product();
		prod.setCategory(cat);
		prod.setName(req.getName());
		prod.setPrice(req.getPrice());
		prod.setQuantity(req.getQuantity());
		
		productRepo.save(prod);

		return ResponseHelper.createResponse(prod, "Product added successfully.", "Failed to add product.");
	}
	
	public MSResponse updateProduct(ProductRequest req, String id) {
		Product prod = null;
		prod = productRepo.findByIdAndIsDeleted(id, false);
		
		if(prod==null) {
			throw new MSException(ErrorCode.NOT_FOUND, "Product doesn't exist.");
		}
		
		if(req.getName()!=null && !req.getName().isBlank()) {
			prod.setName(req.getName());
		}
		if(req.getCategory()!=null && !req.getCategory().isBlank()) {
			prod.setCategory(req.getCategory());
		}
		if(req.getPrice()!=null) {
			prod.setPrice(req.getPrice());
		}
		if(req.getQuantity()!=null) {
			prod.setQuantity(req.getQuantity());
		}
		
		productRepo.save(prod);
		
		return ResponseHelper.createResponse(prod, "Product updated successfully.", "Failed to update product.");
	}
	
	public MSResponse deleteProduct(String id) {
		Product prod = null;
		prod = productRepo.findByIdAndIsDeleted(id, false);
		
		if(prod==null) {
			throw new MSException(ErrorCode.NOT_FOUND, "Product doesn't exist.");
		}
		
		prod.setIsDeleted(true);
		
		productRepo.save(prod);
		
		return ResponseHelper.createResponse("", "Product deleted successfully.", "Failed to delete product.");
	}
}
