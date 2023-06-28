package com.fr.p3p.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fr.p3p.model.TransactionHistory;
import com.fr.p3p.model.request.TransactionRequest;
import com.fr.p3p.model.response.MSResponse;
import com.fr.p3p.repository.TransactionRepository;
import com.fr.p3p.service.TransactionService;
import com.fr.p3p.utils.ResponseHelper;

public class TransactionServiceImpl implements TransactionService {


	@Autowired
	TransactionRepository transRepo;
	
	public MSResponse createPurchase(TransactionRequest req) {
		TransactionHistory trans = new TransactionHistory();
		
		trans.setProduct_id(req.getProduct_id());
		trans.setQuantity(req.getQuantity());
		trans.setUser_id(req.getUser_id());
		trans.setPurchaseDate(LocalDateTime.now());
		
		transRepo.save(trans);
		
		return ResponseHelper.createResponse(trans, "Transaction made successfully.", "Failed to make transaction."); 
	}
	
	public MSResponse getAllPurchases() {
		List<TransactionHistory> transList = transRepo.findAll();
		return ResponseHelper.createResponse(transList, "Transactions retrieved successfully.", "Failed to retrieve transactions."); 
	}
	
	public MSResponse getPurchasesByUserId(String id) {
		List<TransactionHistory> transList = transRepo.findByUserId(id);
		return ResponseHelper.createResponse(transList, "Transactions retrieved successfully.", "Failed to retrieve transactions."); 
	}
	
	public MSResponse getPurchasesByDate(LocalDateTime startDate, LocalDateTime endDate) {
		List<TransactionHistory> purchaseList = transRepo.findByPurchaseDateBetween(startDate, endDate);
		return ResponseHelper.createResponse(purchaseList, "Transactions retrieved successfully.", "Failed to retrieve transactions."); 
	}
}
