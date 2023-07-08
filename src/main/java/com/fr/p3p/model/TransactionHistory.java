package com.fr.p3p.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionHistory {
	
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private String userId;
    
    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "quantity")
    private int quantity;
    
    @Column(name="category",nullable = false)
    private String category;
    
    @Column(nullable = false)
    private Boolean isDeleted=false;
    
    

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getProduct_id() {
		return productId;
	}

	public void setProduct_id(String product_id) {
		this.productId = product_id;
	}

	public String getUser_id() {
		return userId;
	}

	public void setUser_id(String user_id) {
		this.userId = user_id;
	}

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public TransactionHistory() {
		super();
	}
    
    
}
