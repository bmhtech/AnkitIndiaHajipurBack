package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.validation.constraints.Size;

public class Pur_Quotation_DocDTO {

	private String company_id;
	
	private String quotation_no;
	
	private String doc_name;

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getQuotation_no() {
		return quotation_no;
	}

	public void setQuotation_no(String quotation_no) {
		this.quotation_no = quotation_no;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	
}
