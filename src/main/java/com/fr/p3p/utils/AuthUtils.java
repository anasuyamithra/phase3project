package com.fr.p3p.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.p3p.model.User;
import com.fr.p3p.repository.UserRepository;

@Component
public class AuthUtils {
	
	@Autowired
	UserRepository userRepo;
	
	public String checkAuth(String token) {
		User user = userRepo.findBySessionKey(token);
		if(user==null) {
			throw new MSException(ErrorCode.NOT_FOUND, "User doesn't exist."); 
		}
		if(user.getIsAdmin()==false) {
			throw new MSException(ErrorCode.ACCESS_DENIED, "Unauthorised");
		} else {
			return "ok";
		}
	}

}
