package com.fr.p3p.service;

import com.fr.p3p.model.request.ProductRequest;
import com.fr.p3p.model.response.MSResponse;

public interface ProductService {
	
	public MSResponse getAllProducts();
	public MSResponse getProductByCategory(String categories);
	public MSResponse addProduct(ProductRequest req);
	public MSResponse updateProduct(ProductRequest req, String id);
	public MSResponse deleteProduct(String id);

}
