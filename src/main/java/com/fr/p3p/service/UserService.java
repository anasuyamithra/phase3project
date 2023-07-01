package com.fr.p3p.service;

import com.fr.p3p.model.request.AuthRequest;
import com.fr.p3p.model.request.UserRequest;
import com.fr.p3p.model.response.MSResponse;

public interface UserService {
	public MSResponse addUser(UserRequest req);
	public MSResponse getAllUsers(String token);
	public MSResponse getUserById(String id, String token);
	public MSResponse deleteUser(String id, String token);
	public MSResponse login(AuthRequest req);
	public MSResponse updateUser(UserRequest req, String id, String token);
	public MSResponse logout(String token);
}
