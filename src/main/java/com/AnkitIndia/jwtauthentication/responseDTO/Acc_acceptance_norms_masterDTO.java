package com.AnkitIndia.jwtauthentication.responseDTO;

public class Acc_acceptance_norms_masterDTO {

	private String anm_id;
	
	private String anm_code; 
	
	private String anm_description;
	
	private boolean anm_active;

	public String getAnm_id() {
		return anm_id;
	}

	public void setAnm_id(String anm_id) {
		this.anm_id = anm_id;
	}

	public String getAnm_code() {
		return anm_code;
	}

	public void setAnm_code(String anm_code) {
		this.anm_code = anm_code;
	}

	public String getAnm_description() {
		return anm_description;
	}

	public void setAnm_description(String anm_description) {
		this.anm_description = anm_description;
	}

	public boolean isAnm_active() {
		return anm_active;
	}

	public void setAnm_active(boolean anm_active) {
		this.anm_active = anm_active;
	}
	
	

}
