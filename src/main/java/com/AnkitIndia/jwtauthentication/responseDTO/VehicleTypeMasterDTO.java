package com.AnkitIndia.jwtauthentication.responseDTO;

public class VehicleTypeMasterDTO {
	
	private String vehtype_code;
	
	private String vehtype_name;
	
	private boolean vehtype_active;

	public String getVehtype_code() {
		return vehtype_code;
	}

	public void setVehtype_code(String vehtype_code) {
		this.vehtype_code = vehtype_code;
	}

	public String getVehtype_name() {
		return vehtype_name;
	}

	public void setVehtype_name(String vehtype_name) {
		this.vehtype_name = vehtype_name;
	}

	public boolean isVehtype_active() {
		return vehtype_active;
	}

	public void setVehtype_active(boolean vehtype_active) {
		this.vehtype_active = vehtype_active;
	}
	
	
	
}
