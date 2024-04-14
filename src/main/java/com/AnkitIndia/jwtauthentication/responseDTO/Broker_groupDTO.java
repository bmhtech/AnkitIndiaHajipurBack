package com.AnkitIndia.jwtauthentication.responseDTO;

public class Broker_groupDTO {
	
	private String group_id;
	
	private String group_name;
	
	private String parent_group;
	
	private String company;

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getParent_group() {
		return parent_group;
	}

	public void setParent_group(String parent_group) {
		this.parent_group = parent_group;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Broker_groupDTO() {
		super();
	}

	public Broker_groupDTO(String group_id, String group_name, String parent_group, String company) {
		super();
		this.group_id = group_id;
		this.group_name = group_name;
		this.parent_group = parent_group;
		this.company = company;
	}

}
