package com.AnkitIndia.jwtauthentication.model;

import java.io.Serializable;

public class UserRolesCompositePK implements Serializable {
	
	long user_id;
	
	long role_id;

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

}
