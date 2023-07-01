package com.fr.p3p.service;

import com.fr.p3p.model.request.ProductRequest;
import com.fr.p3p.model.response.MSResponse;

public interface ProductService {
	
	public MSResponse getAllProducts();
	public MSResponse getProductByCategory(String categories);
	public MSResponse addProduct(ProductRequest req, String token);
	public MSResponse updateProduct(ProductRequest req, String id, String token);
	public MSResponse deleteProduct(String id, String token);

}
