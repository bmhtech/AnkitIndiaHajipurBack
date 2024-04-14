package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.validation.constraints.Size;

public class Cust_bussiness_partner_docDTO {
	
	private String cp_Id;
	
	private String company_id;
	
	private String doc_name;

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

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	

}
