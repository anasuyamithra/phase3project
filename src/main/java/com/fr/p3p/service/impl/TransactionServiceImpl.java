package com.fr.p3p.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.p3p.model.Product;
import com.fr.p3p.model.TransactionHistory;
import com.fr.p3p.model.User;
import com.fr.p3p.model.request.TransactionRequest;
import com.fr.p3p.model.response.MSResponse;
import com.fr.p3p.repository.ProductRepository;
import com.fr.p3p.repository.TransactionRepository;
import com.fr.p3p.repository.UserRepository;
import com.fr.p3p.service.TransactionService;
import com.fr.p3p.utils.AuthUtils;
import com.fr.p3p.utils.ErrorCode;
import com.fr.p3p.utils.MSException;
import com.fr.p3p.utils.ResponseHelper;

@Service
public class TransactionServiceImpl implements TransactionService {


	@Autowired
	TransactionRepository transRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AuthUtils auth; 
	
	public MSResponse createPurchase(TransactionRequest req, String token) {
		TransactionHistory trans = new TransactionHistory();
		Product cat = productRepo.findByIdAndIsDeleted(req.getProduct_id(), false);
		
		if(cat == null) {
			throw new MSException(ErrorCode.NOT_FOUND, "Product doesn't exist.");
		}
		
		User user=userRepo.findBySessionKey(token);
		
		trans.setProduct_id(req.getProduct_id());
		trans.setCategory(cat.getCategory());
		trans.setQuantity(req.getQuantity());
		trans.setUser_id(user.getId());
		trans.setPurchaseDate(LocalDateTime.now());
		
		transRepo.save(trans);
		
		return ResponseHelper.createResponse(trans, "Transaction made successfully.", "Failed to make transaction."); 
	}
	
	public MSResponse getAllPurchases(String token) {
		String s = auth.checkAuth(token);
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
	
	public MSResponse getPurchasesByCategory(String cat) {
		List<TransactionHistory> purchaseList = transRepo.findByCategoryAndIsDeleted(cat, false);
		if(purchaseList.isEmpty()) {
			throw new MSException(ErrorCode.BAD_REQUEST, "No purchases in this category.");
		}
		return ResponseHelper.createResponse(purchaseList, "Transactions retrieved successfully.", "Failed to retrieve transactions."); 
	}

	public MSResponse deletePurchase(String id, String token) {
		String s = auth.checkAuth(token);
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
