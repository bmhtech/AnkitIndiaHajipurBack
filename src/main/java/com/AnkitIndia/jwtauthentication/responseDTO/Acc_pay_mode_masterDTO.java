package com.AnkitIndia.jwtauthentication.responseDTO;

public class Acc_pay_mode_masterDTO {
	
	private String paymode_id;
	
	private String paymode_code;
	
	private String paymode_name;
	
	private boolean  paymode_active;

	public String getPaymode_id() {
		return paymode_id;
	}

	public void setPaymode_id(String paymode_id) {
		this.paymode_id = paymode_id;
	}

	public String getPaymode_code() {
		return paymode_code;
	}

	public void setPaymode_code(String paymode_code) {
		this.paymode_code = paymode_code;
	}

	public String getPaymode_name() {
		return paymode_name;
	}

	public void setPaymode_name(String paymode_name) {
		this.paymode_name = paymode_name;
	}

	public boolean isPaymode_active() {
		return paymode_active;
	}

	public void setPaymode_active(boolean paymode_active) {
		this.paymode_active = paymode_active;
	}
	
	
	
}
