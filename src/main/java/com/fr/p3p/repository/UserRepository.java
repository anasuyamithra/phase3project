package com.fr.p3p.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.p3p.model.User;
import com.fr.p3p.model.response.MSResponse;

public interface UserRepository extends JpaRepository<User, String> {
	
	User findByUsernameAndIsDeleted(String username, Boolean isDeleted);
	User findByEmailAndIsDeleted(String username, Boolean isDeleted);
	User findByIdAndIsDeleted(String id, Boolean isDeleted);
	MSResponse getUserById(String id);
	List<User> findByIsDeleted(boolean IsDeleted);
	User findBySessionKey(String token);
	
}
