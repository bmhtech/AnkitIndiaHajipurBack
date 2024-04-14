package com.AnkitIndia.jwtauthentication.responseDTO;

public class TransportationChgsMatrixMasterDTO {
	
	private String tcm_id;
	
	private String tcm_code;
	
	private String tcm_description;
	
	private boolean tcm_active;

	public String getTcm_id() {
		return tcm_id;
	}

	public void setTcm_id(String tcm_id) {
		this.tcm_id = tcm_id;
	}

	public String getTcm_code() {
		return tcm_code;
	}

	public void setTcm_code(String tcm_code) {
		this.tcm_code = tcm_code;
	}

	public String getTcm_description() {
		return tcm_description;
	}

	public void setTcm_description(String tcm_description) {
		this.tcm_description = tcm_description;
	}

	public boolean isTcm_active() {
		return tcm_active;
	}

	public void setTcm_active(boolean tcm_active) {
		this.tcm_active = tcm_active;
	}
	
	

}
