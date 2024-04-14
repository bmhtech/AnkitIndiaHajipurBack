package com.AnkitIndia.jwtauthentication.transResponseDTO;

import javax.validation.constraints.Size;

public class Broker_master_docDTO {
	
	private String broker_Id;
	
	private String company_id;
	
	private String broker_code;
	
	private String doc_name;

	public String getBroker_Id() {
		return broker_Id;
	}

	public void setBroker_Id(String broker_Id) {
		this.broker_Id = broker_Id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getBroker_code() {
		return broker_code;
	}

	public void setBroker_code(String broker_code) {
		this.broker_code = broker_code;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

}
