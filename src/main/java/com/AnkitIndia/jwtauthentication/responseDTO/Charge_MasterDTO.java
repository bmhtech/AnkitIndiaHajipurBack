package com.AnkitIndia.jwtauthentication.responseDTO;


public class Charge_MasterDTO {

	private String charge_id;
	
	private String screen_id;
	
	private String charge_desc;

	public String getCharge_id() {
		return charge_id;
	}

	public void setCharge_id(String charge_id) {
		this.charge_id = charge_id;
	}

	public String getScreen_id() {
		return screen_id;
	}

	public void setScreen_id(String screen_id) {
		this.screen_id = screen_id;
	}

	public String getCharge_desc() {
		return charge_desc;
	}

	public void setCharge_desc(String charge_desc) {
		this.charge_desc = charge_desc;
	}

	public Charge_MasterDTO() {
		super();
	}

	public Charge_MasterDTO(String charge_id, String screen_id, String charge_desc) {
		super();
		this.charge_id = charge_id;
		this.screen_id = screen_id;
		this.charge_desc = charge_desc;
	}
	
	
}