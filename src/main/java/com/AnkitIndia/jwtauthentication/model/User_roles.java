package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.AnkitIndia.jwtauthentication.model.UserRolesCompositePK;

@Entity @IdClass(UserRolesCompositePK.class)
@Table(name="User_roles")
public class User_roles {
	
	@Id long user_id;
	
	@Id long role_id;
	
	@Lob
	String roleaccessjson;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getRole_id() {
		return role_id;
	}

	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}

	public String getRoleaccessjson() {
		return roleaccessjson;
	}

	public void setRoleaccessjson(String roleaccessjson) {
		this.roleaccessjson = roleaccessjson;
	}
	
	
}
