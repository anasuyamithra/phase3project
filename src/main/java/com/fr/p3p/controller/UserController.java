package com.fr.p3p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public MSResponse signup(@RequestBody UserRequest req) {
		return userService.addUser(req);
	}
	
	@GetMapping
	public MSResponse getAllUsers(@RequestParam String token) {
		return userService.getAllUsers(token);
	}
	
	@GetMapping("/{id}")
	public MSResponse getUserById(@PathVariable String id, @RequestParam String token) {
		return userService.getUserById(id, token);
	}
	
	@DeleteMapping("/{id}")
	public MSResponse deleteUser(@PathVariable String id, @RequestParam String token) {
		return userService.deleteUser(id, token);
	}
	
	@RequestMapping("/login")
	public MSResponse login(@RequestParam(required=true) String username, @RequestParam(required=true) String password) {
		AuthRequest req=new AuthRequest();
		req.setPassword(password);
		req.setUsername(username);
		return userService.login(req);
	}
	
	@PutMapping("/admin/update/{id}")
	public MSResponse updateUser(@RequestBody UserRequest req,
									@PathVariable String id, @RequestParam String token) {
		return userService.updateUser(req, id, token);
	}
	
	@PostMapping("/logout")
	public MSResponse logout(@RequestParam String token) {
		return userService.logout(token);
	}
	
}
