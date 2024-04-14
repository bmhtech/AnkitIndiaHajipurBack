package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Role;
import com.AnkitIndia.jwtauthentication.model.User_roles;
import com.AnkitIndia.jwtauthentication.model.Userroles;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface RoleService {

	public Role save(Role role);
	
	public List<Role> getRoles();
	
	public List<Role> getRolesThruUserId(String user);
	
	public StatusDTO updateUserRoles(Userroles uRoles);
	
	public User_roles getUserRoles(String user,String role);
	
}
