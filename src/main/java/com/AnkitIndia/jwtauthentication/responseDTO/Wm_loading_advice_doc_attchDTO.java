package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.validation.constraints.Size;

public class Wm_loading_advice_doc_attchDTO {

	private String advice_id;
	
	private String company_id;
	
	private String advice_no;
	
	private String doc_name;

	private String  doc_pdf;
	
	
	
	
	public String getDoc_pdf() {
		return doc_pdf;
	}

	public void setDoc_pdf(String doc_pdf) {
		this.doc_pdf = doc_pdf;
	}

	public String getAdvice_id() {
		return advice_id;
	}

	public void setAdvice_id(String advice_id) {
		this.advice_id = advice_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getAdvice_no() {
		return advice_no;
	}

	public void setAdvice_no(String advice_no) {
		this.advice_no = advice_no;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	
}
