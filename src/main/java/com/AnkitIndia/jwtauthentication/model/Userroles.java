package com.AnkitIndia.jwtauthentication.model;

public class Userroles {

	private String user_id;
	
	private String role_id;
	
	private String parent_role_id;
	
	private String roleaccessjson;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	public String getParent_role_id() {
		return parent_role_id;
	}

	public void setParent_role_id(String parent_role_id) {
		this.parent_role_id = parent_role_id;
	}

	public String getRoleaccessjson() {
		return roleaccessjson;
	}

	public void setRoleaccessjson(String roleaccessjson) {
		this.roleaccessjson = roleaccessjson;
	}
	
}
