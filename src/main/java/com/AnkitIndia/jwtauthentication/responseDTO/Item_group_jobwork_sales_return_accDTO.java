package com.AnkitIndia.jwtauthentication.responseDTO;

public class Item_group_jobwork_sales_return_accDTO {

	private Long id;
	
	private String item_group_code;
	
	private String item_group_id;

	private String [] jw_sr_item_total;
	
	private String jw_sr_item_array;
	
	private String jw_sr_discount;

	private String jw_sr_adjplus;
	
	private String jw_sr_adjminus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem_group_code() {
		return item_group_code;
	}

	public void setItem_group_code(String item_group_code) {
		this.item_group_code = item_group_code;
	}

	public String getItem_group_id() {
		return item_group_id;
	}

	public void setItem_group_id(String item_group_id) {
		this.item_group_id = item_group_id;
	}

	public String[] getJw_sr_item_total() {
		return jw_sr_item_total;
	}

	public void setJw_sr_item_total(String[] jw_sr_item_total) {
		this.jw_sr_item_total = jw_sr_item_total;
	}

	public String getJw_sr_item_array() {
		return jw_sr_item_array;
	}

	public void setJw_sr_item_array(String jw_sr_item_array) {
		this.jw_sr_item_array = jw_sr_item_array;
	}

	public String getJw_sr_discount() {
		return jw_sr_discount;
	}

	public void setJw_sr_discount(String jw_sr_discount) {
		this.jw_sr_discount = jw_sr_discount;
	}

	public String getJw_sr_adjplus() {
		return jw_sr_adjplus;
	}

	public void setJw_sr_adjplus(String jw_sr_adjplus) {
		this.jw_sr_adjplus = jw_sr_adjplus;
	}

	public String getJw_sr_adjminus() {
		return jw_sr_adjminus;
	}

	public void setJw_sr_adjminus(String jw_sr_adjminus) {
		this.jw_sr_adjminus = jw_sr_adjminus;
	}
	

}
