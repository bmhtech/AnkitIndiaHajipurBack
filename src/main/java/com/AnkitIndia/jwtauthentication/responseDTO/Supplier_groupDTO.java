package com.AnkitIndia.jwtauthentication.responseDTO;


public class Supplier_groupDTO {
	
	private String  bp_group_id;
	
	private String bp_group_code;
	
	private String bp_grp_name;
	
	private String parent_group;

	public String getBp_group_id() {
		return bp_group_id;
	}

	public void setBp_group_id(String bp_group_id) {
		this.bp_group_id = bp_group_id;
	}

	public String getBp_grp_name() {
		return bp_grp_name;
	}

	public void setBp_grp_name(String bp_grp_name) {
		this.bp_grp_name = bp_grp_name;
	}

	public String getBp_group_code() {
		return bp_group_code;
	}

	public void setBp_group_code(String bp_group_code) {
		this.bp_group_code = bp_group_code;
	}

	public String getParent_group() {
		return parent_group;
	}

	public void setParent_group(String parent_group) {
		this.parent_group = parent_group;
	}

	public Supplier_groupDTO() {
		super();
	}

	public Supplier_groupDTO(String bp_group_id, String bp_group_code, String bp_grp_name, String parent_group) {
		super();
		this.bp_group_id = bp_group_id;
		this.bp_group_code = bp_group_code;
		this.bp_grp_name = bp_grp_name;
		this.parent_group = parent_group;
	}
	
	

}
