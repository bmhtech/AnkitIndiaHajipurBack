package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Item_master_qc_detailsDTO {
	
	private String item_id;
	
	private String item_code;
	
	private String company_id;
	
	private int sl_no;
	
	private String qc_id;
	
	private String qc_code;
	
	private String qc_description;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
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

	public String getQc_id() {
		return qc_id;
	}

	public void setQc_id(String qc_id) {
		this.qc_id = qc_id;
	}

	public String getQc_code() {
		return qc_code;
	}

	public void setQc_code(String qc_code) {
		this.qc_code = qc_code;
	}

	public String getQc_description() {
		return qc_description;
	}

	public void setQc_description(String qc_description) {
		this.qc_description = qc_description;
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

	public LocalDateTime getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	
	
}
