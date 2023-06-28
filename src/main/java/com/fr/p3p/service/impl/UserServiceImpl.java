package com.fr.p3p.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.p3p.model.User;
import com.fr.p3p.model.request.AuthRequest;
import com.fr.p3p.model.request.UserRequest;
import com.fr.p3p.model.response.MSResponse;
import com.fr.p3p.repository.UserRepository;
import com.fr.p3p.service.UserService;
import com.fr.p3p.utils.ErrorCode;
import com.fr.p3p.utils.MSException;
import com.fr.p3p.utils.ResponseHelper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public MSResponse addUser(UserRequest req) {
		User user = null;
		User usere = null;
		
		user = userRepo.findByUsernameAndIsDeleted(req.getUsername(), false);
		usere = userRepo.findByEmailAndIsDeleted(req.getEmail(), false);
		
		if(usere == null && user==null) {
			user = new User();
			user.setEmail(req.getEmail());
			user.setPassword(req.getPassword());
			user.setUsername(req.getUsername());
			if(req.getIsAdmin()!=null)
				user.setIsAdmin(req.getIsAdmin());
		} else {
			 throw new MSException(ErrorCode.BAD_REQUEST, "User already exists.");
		}
		
		userRepo.save(user);

		return ResponseHelper.createResponse(user, "User added successfully.", "Failed to add User.");
	}
	
	public MSResponse getAllUsers() {
		List<User> users = userRepo.findByIsDeleted(false);
		return ResponseHelper.createResponse(users, "Users retrieved successfully.", "Failed to retrieve users.");
	}
	
	public MSResponse getUserById(String id) {
		User user = userRepo.findByIdAndIsDeleted(id, false);
		return ResponseHelper.createResponse(user, "User retrieved successfully.", "Failed to retrieve user.");
	}
	
	public MSResponse deleteUser(String id) {
		User user = null;
		user = userRepo.findByIdAndIsDeleted(id, false);
		
		if(user==null) {
			throw new MSException(ErrorCode.NOT_FOUND, "User doesn't exist.");
		}
		
		user.setIsDeleted(true);
		
		userRepo.save(user);
		
		return ResponseHelper.createResponse("", "User deleted successfully.", "Failed to delete user.");
	}
	
	public MSResponse login(AuthRequest req) {
		User user=null;
		user=userRepo.findByUsernameAndIsDeleted(req.getUsername(), false);
		
		if(user==null) {
			throw new MSException(ErrorCode.NOT_FOUND, "User not found.");
		}
		
		if(!user.getPassword().equals(req.getPassword())) {
			throw new MSException(ErrorCode.BAD_REQUEST, "Wrong Password.");
		}
		
		return ResponseHelper.createResponse("", "Logged in successfully.", "Failed to login.");
	}

	@Override
	public MSResponse updateUser(UserRequest req, String id) {
		User user = null;
		user = userRepo.findByIdAndIsDeleted(id, false);
		
		if(user==null) {
			throw new MSException(ErrorCode.NOT_FOUND, "User not found.");
		}
		
		if(req.getUsername()!=null && req.getUsername().isBlank()==false) {
			user.setUsername(req.getUsername());
		}
		if(req.getEmail()!=null && req.getEmail().isBlank()==false) {
			user.setEmail(req.getEmail());
		}
		if(req.getPassword()!=null && req.getPassword().isBlank()==false) {
			user.setPassword(req.getPassword());
		}
		if(req.getIsAdmin()!=null) {
			user.setIsAdmin(req.getIsAdmin());
		}
		
		userRepo.save(user);
		
		return ResponseHelper.createResponse(user, "User updated successfully.", "Failed to update user.");
	}

}
