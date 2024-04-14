package com.AnkitIndia.jwtauthentication.responseDTO;


public class Pur_Order_DocDTO {

	private String pur_order_no;

	private String company_id;
	
	private String doc_name;

	public String getPur_order_no() {
		return pur_order_no;
	}

	public void setPur_order_no(String pur_order_no) {
		this.pur_order_no = pur_order_no;
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
