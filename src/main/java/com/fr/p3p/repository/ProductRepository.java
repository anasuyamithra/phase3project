package com.fr.p3p.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.p3p.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	
		List<Product> findByIsDeleted(Boolean isDeleted);
		List<Product> findByCategory(String category);
		Product findByNameAndCategoryAndIsDeleted(String name, String category, Boolean isDeleted);
		Product findByIdAndIsDeleted(String id, Boolean isDeleted);
}
