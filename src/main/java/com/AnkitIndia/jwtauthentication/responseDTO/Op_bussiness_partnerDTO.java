package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.validation.constraints.Size;

//@Entity
//@Table(name="Op_bussiness_partner")
public class Op_bussiness_partnerDTO {
	
	
	
	@Size(max = 50)
	private String bp_Id;
	
	
	@Size(max = 100)
	private String bp_name;


	public String getBp_Id() {
		return bp_Id;
	}


	public void setBp_Id(String bp_Id) {
		this.bp_Id = bp_Id;
	}


	public String getBp_name() {
		return bp_name;
	}


	public void setBp_name(String bp_name) {
		this.bp_name = bp_name;
	}
	
	
	
	
}
