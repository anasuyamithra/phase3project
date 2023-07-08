package com.fr.p3p.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.p3p.model.TransactionHistory;

public interface TransactionRepository extends JpaRepository<TransactionHistory, String> {
	List<TransactionHistory> findByUserId(String UserId);
	List<TransactionHistory> findByPurchaseDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	List<TransactionHistory> findByIsDeleted(boolean b);
	TransactionHistory findByIdAndIsDeleted(String id, boolean b);
	List<TransactionHistory> findByCategoryAndIsDeleted(String category, boolean b);
}
