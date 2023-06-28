package com.fr.p3p.service;

import java.time.LocalDateTime;

import com.fr.p3p.model.request.TransactionRequest;
import com.fr.p3p.model.response.MSResponse;

public interface TransactionService {

	MSResponse createPurchase(TransactionRequest req);

	MSResponse getAllPurchases();
	
	MSResponse getPurchasesByDate(LocalDateTime startDate, LocalDateTime endDate);
	
	MSResponse getPurchasesByUserId(String id);

}
