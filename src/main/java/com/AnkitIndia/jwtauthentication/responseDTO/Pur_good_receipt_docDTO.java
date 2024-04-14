package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Pur_good_receipt_docDTO {

	private Long id;
	
	private String grn_id;
	
	private String grn_no;
	
	private String doc_name;
	
	private String company_id;
	
	private String fin_year;
	
	private String modified_type;
	
	private String inserted_by;
	
	private LocalDateTime inserted_on;
	
	private String updated_by;
	
	private LocalDateTime updated_on;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGrn_id() {
		return grn_id;
	}

	public void setGrn_id(String grn_id) {
		this.grn_id = grn_id;
	}

	public String getGrn_no() {
		return grn_no;
	}

	public void setGrn_no(String grn_no) {
		this.grn_no = grn_no;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}

	public String getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}

	public LocalDateTime getInserted_on() {
		return inserted_on;
	}

	public void setInserted_on(LocalDateTime inserted_on) {
		this.inserted_on = inserted_on;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public LocalDateTime getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}
	
	
}
