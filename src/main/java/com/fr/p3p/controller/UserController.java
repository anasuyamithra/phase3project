package com.fr.p3p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.p3p.model.request.AuthRequest;
import com.fr.p3p.model.request.UserRequest;
import com.fr.p3p.model.response.MSResponse;
import com.fr.p3p.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public MSResponse addUser(@RequestBody UserRequest req) {
		return userService.addUser(req);
	}
	
	@GetMapping
	public MSResponse getAllProducts() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public MSResponse getProductByCategory(@PathVariable String id) {
		return userService.getUserById(id);
	}
	
	@DeleteMapping("/{id}")
	public MSResponse deleteUser(@PathVariable String id) {
		return userService.deleteUser(id);
	}
	
	@RequestMapping("/login")
	public MSResponse login(@RequestBody AuthRequest req) {
		return userService.login(req);
	}
	
	
}
