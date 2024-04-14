package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.validation.constraints.Size;

public class Op_bussiness_partner_brokerDTO {

	private String bp_Id;
	
	private String company_id;
	
	private int sl_no;
	
	private String broker_name;
	
	private String broker_code;

	public String getBp_Id() {
		return bp_Id;
	}

	public void setBp_Id(String bp_Id) {
		this.bp_Id = bp_Id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getBroker_name() {
		return broker_name;
	}

	public void setBroker_name(String broker_name) {
		this.broker_name = broker_name;
	}

	public String getBroker_code() {
		return broker_code;
	}

	public void setBroker_code(String broker_code) {
		this.broker_code = broker_code;
	}
	
}
