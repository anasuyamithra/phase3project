package com.fr.p3p.service;

import com.fr.p3p.model.request.AuthRequest;
import com.fr.p3p.model.request.UserRequest;
import com.fr.p3p.model.response.MSResponse;

public interface UserService {
	public MSResponse addUser(UserRequest req);
	public MSResponse getAllUsers();
	public MSResponse getUserById(String id);
	public MSResponse deleteUser(String id);
	public MSResponse login(AuthRequest req);
	public MSResponse updateUser(UserRequest req, String id);
}
