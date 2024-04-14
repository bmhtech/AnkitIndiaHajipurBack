package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.User_Role_Access;


public interface User_Role_AccessService {

	public User_Role_Access save(User_Role_Access user_Role_Access);
	
	public List<User_Role_Access> getRoleItemMaster(String role_access);
	
	public List<User_Role_Access> findAll();
	
	public User_Role_Access getUserroleAccessperrole(String role);
}
