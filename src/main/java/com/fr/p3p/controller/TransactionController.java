package com.fr.p3p.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.p3p.model.request.TransactionRequest;
import com.fr.p3p.model.response.MSResponse;
import com.fr.p3p.service.TransactionService;

@RestController
@RequestMapping("/purchase")
public class TransactionController {

	@Autowired
	TransactionService transService;
	
	@PostMapping
	public MSResponse createPurchase(@RequestBody TransactionRequest req, @RequestParam String token) {
		return transService.createPurchase(req);
	}
	
	@GetMapping
	public MSResponse getAllPurchases(@RequestParam String token) {
		return transService.getAllPurchases(token);
	}
	
	@GetMapping("/{id}")
	public MSResponse getPurchasesByUserId(@PathVariable String id, @RequestParam String token) {
		return transService.getPurchasesByUserId(id);
	}
	
	@GetMapping("/date")
	public MSResponse getPurchasesByDate(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate, @RequestParam String token) {
		
		//USE FORMAT AS FOLLOWS: startDate=2023-06-27T09:00:00     endDate=2023-06-28T18:00:00   (YYYY-MM-DDTHH:MM:SS)

		return transService.getPurchasesByDate(startDate, endDate);
	}
	
	@DeleteMapping("/{id}")
	public MSResponse deletePurchaseReport(@PathVariable String id, @RequestParam String token) {
		return transService.deletePurchase(id, token);
	}
	
}
