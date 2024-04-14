package com.AnkitIndia.jwtauthentication.responseDTO;


public class Item_type_masterDTO {
	
	private Long id;
	
	private String item_id;
	
	private String item_code;
	
	private String item_name;
	
	private String item_active;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	
	public String getItem_active() {
		return item_active;
	}

	public void setItem_active(String item_active) {
		this.item_active = item_active;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public Item_type_masterDTO() {
		super();
	}

	public Item_type_masterDTO(String item_id, String item_code, String item_name, String item_active) {
		super();
		this.item_id = item_id;
		this.item_code = item_code;
		this.item_name = item_name;
		this.item_active = item_active;
	}
	
	
}
