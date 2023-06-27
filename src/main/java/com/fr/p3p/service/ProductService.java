package com.fr.p3p.service;

import com.fr.p3p.model.response.MSResponse;

public interface ProductService {
	
	public MSResponse getAllProducts();
	public MSResponse getProductByCategory(String categories);

}
