package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

public class Trans_bussiness_partner_docDTO {

	private String doc_name;
	
	private String doc_pdf;
	
	private String fin_year;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;

	public String getDoc_pdf() {
		return doc_pdf;
	}

	public void setDoc_pdf(String doc_pdf) {
		this.doc_pdf = doc_pdf;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public LocalDateTime getInserted_on() {
		return inserted_on;
	}

	public void setInserted_on(LocalDateTime inserted_on) {
		this.inserted_on = inserted_on;
	}

	public String getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}
	
	
	
}
