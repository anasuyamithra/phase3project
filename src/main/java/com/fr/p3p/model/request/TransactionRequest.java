package com.fr.p3p.model.request;

import java.time.LocalDateTime;

public class TransactionRequest {
	
    private String productId;
    private String userId;
    private LocalDateTime purchaseDate;
    private int quantity;
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
}
