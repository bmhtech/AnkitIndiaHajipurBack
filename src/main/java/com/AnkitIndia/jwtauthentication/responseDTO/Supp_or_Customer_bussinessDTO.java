package com.AnkitIndia.jwtauthentication.responseDTO;

public class Supp_or_Customer_bussinessDTO {

     private String bp_Id;
	
	
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


	@Override
	public String toString() {
		return "Supp_or_Customer_bussinessDTO [bp_Id=" + bp_Id + ", bp_name=" + bp_name + "]";
	}


	
	
	
	
	
}
