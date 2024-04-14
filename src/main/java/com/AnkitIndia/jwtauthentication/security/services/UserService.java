package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.User;
import com.AnkitIndia.jwtauthentication.responseDTO.UserDTO;

public interface UserService {

	public List<User> getUsers();
	
	public List<UserDTO> getRolesNameList(String role);
	
	public List<User> getUserEmailDuplicateCheck();
	
	public Boolean getUserEmailPswdDuplicateCheck(String username,String pasword);
	
}
