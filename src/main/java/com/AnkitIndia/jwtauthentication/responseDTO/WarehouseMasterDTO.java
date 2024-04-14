package com.AnkitIndia.jwtauthentication.responseDTO;

public class WarehouseMasterDTO {

	private String warehouse_code;
	
	private String warehouse_name;
	
	private boolean warehouse_active;

	public String getWarehouse_code() {
		return warehouse_code;
	}

	public void setWarehouse_code(String warehouse_code) {
		this.warehouse_code = warehouse_code;
	}

	public String getWarehouse_name() {
		return warehouse_name;
	}

	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}

	public boolean isWarehouse_active() {
		return warehouse_active;
	}

	public void setWarehouse_active(boolean warehouse_active) {
		this.warehouse_active = warehouse_active;
	}

}
