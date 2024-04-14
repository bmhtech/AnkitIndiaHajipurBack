package com.AnkitIndia.jwtauthentication.responseDTO;

public class Acc_pay_term_masterDTO {
	
	private String payterm_id;
	
	private String payterm_desc;
	
	private boolean  payterm_active;

	public String getPayterm_id() {
		return payterm_id;
	}

	public void setPayterm_id(String payterm_id) {
		this.payterm_id = payterm_id;
	}
	
	
	public String getPayterm_desc() {
		return payterm_desc;
	}

	public void setPayterm_desc(String payterm_desc) {
		this.payterm_desc = payterm_desc;
	}

	public boolean isPayterm_active() {
		return payterm_active;
	}

	public void setPayterm_active(boolean payterm_active) {
		this.payterm_active = payterm_active;
	}

	public Acc_pay_term_masterDTO() {
		super();
	}

	public Acc_pay_term_masterDTO(String payterm_id, String payterm_desc, boolean payterm_active) {
		super();
		this.payterm_id = payterm_id;
		this.payterm_desc = payterm_desc;
		this.payterm_active = payterm_active;
	}
	
		
	
}
