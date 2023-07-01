package com.fr.p3p.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.p3p.model.TransactionHistory;
import com.fr.p3p.model.request.TransactionRequest;
import com.fr.p3p.model.response.MSResponse;
import com.fr.p3p.repository.TransactionRepository;
import com.fr.p3p.service.TransactionService;
import com.fr.p3p.utils.ErrorCode;
import com.fr.p3p.utils.MSException;
import com.fr.p3p.utils.ResponseHelper;

@Service
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
		List<TransactionHistory> transList = transRepo.findByIsDeleted(false);
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

	@Override
	public MSResponse deletePurchase(String id) {
		TransactionHistory trans = null;
		trans = transRepo.findByIdAndIsDeleted(id, false);
		
		if(trans==null) {
			throw new MSException(ErrorCode.NOT_FOUND, "Transaction doesn't exist.");
		}
		
		trans.setIsDeleted(true);
		
		transRepo.save(trans);
		
		return ResponseHelper.createResponse("", "Transaction deleted successfully.", "Failed to delete transaction.");
	}
}
