package com.AnkitIndia.jwtauthentication.responseDTO;


public class Item_group_masterDTO {
	
	private String item_group_id;
	
	private String item_group_code;
	
	private String group_name;
	
	private String group_active;

	private String parent_group;
	
	private String group_type;
	
	public Item_group_masterDTO() {
		super();
	}

	public Item_group_masterDTO(String item_group_id, String item_group_code, String group_name, String group_active,
			String parent_group,String group_type) {
		super();
		this.item_group_id = item_group_id;
		this.item_group_code = item_group_code;
		this.group_name = group_name;
		this.group_active = group_active;
		this.parent_group = parent_group;
		this.group_type = group_type;
	}

	public String getGroup_type() {
		return group_type;
	}

	public void setGroup_type(String group_type) {
		this.group_type = group_type;
	}

	public String getItem_group_id() {
		return item_group_id;
	}

	public void setItem_group_id(String item_group_id) {
		this.item_group_id = item_group_id;
	}

	public String getItem_group_code() {
		return item_group_code;
	}

	public void setItem_group_code(String item_group_code) {
		this.item_group_code = item_group_code;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getGroup_active() {
		return group_active;
	}

	public void setGroup_active(String group_active) {
		this.group_active = group_active;
	}

	public String getParent_group() {
		return parent_group;
	}

	public void setParent_group(String parent_group) {
		this.parent_group = parent_group;
	}
	
	
}
