package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Cust_bussiness_partner_statutoryDTO {
	
	private String cp_Id;
	
	private String company_id;
	
	private boolean registered;  
	
	private String pan_no;
	
	private String gst_no;
	
	private String cin_no;
	
	private String tan_no;
	
	private String customer_type;

	public String getCp_Id() {
		return cp_Id;
	}

	public void setCp_Id(String cp_Id) {
		this.cp_Id = cp_Id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getGst_no() {
		return gst_no;
	}

	public void setGst_no(String gst_no) {
		this.gst_no = gst_no;
	}

	public String getCin_no() {
		return cin_no;
	}

	public void setCin_no(String cin_no) {
		this.cin_no = cin_no;
	}

	public String getTan_no() {
		return tan_no;
	}

	public void setTan_no(String tan_no) {
		this.tan_no = tan_no;
	}

}
