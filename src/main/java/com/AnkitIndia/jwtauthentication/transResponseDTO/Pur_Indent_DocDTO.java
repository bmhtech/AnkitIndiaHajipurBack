package com.AnkitIndia.jwtauthentication.transResponseDTO;

import javax.validation.constraints.Size;

public class Pur_Indent_DocDTO {

	private String indent_no;
	
	private String company_id;
	
	private String doc_name;

	public String getIndent_no() {
		return indent_no;
	}

	public void setIndent_no(String indent_no) {
		this.indent_no = indent_no;
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
