package com.AnkitIndia.jwtauthentication.transResponseDTO;

public class Bom_listDTO {

	private Long id;
	
	private String production_id;
	
	private String production_code;
	
	private String business_unitname;
	
	private String shop_floorname;
	
	private String prod_desc;
	
	private String entry_mode;
	
	private boolean status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduction_id() {
		return production_id;
	}

	public void setProduction_id(String production_id) {
		this.production_id = production_id;
	}

	public String getProduction_code() {
		return production_code;
	}

	public void setProduction_code(String production_code) {
		this.production_code = production_code;
	}

	public String getBusiness_unitname() {
		return business_unitname;
	}

	public void setBusiness_unitname(String business_unitname) {
		this.business_unitname = business_unitname;
	}

	public String getShop_floorname() {
		return shop_floorname;
	}

	public void setShop_floorname(String shop_floorname) {
		this.shop_floorname = shop_floorname;
	}

	public String getProd_desc() {
		return prod_desc;
	}

	public void setProd_desc(String prod_desc) {
		this.prod_desc = prod_desc;
	}

	public String getEntry_mode() {
		return entry_mode;
	}

	public void setEntry_mode(String entry_mode) {
		this.entry_mode = entry_mode;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
