package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

public class Acc_pay_term_master_detailsDTO {

	private String payterm_code;
	
	private String company_id;
	
	private String payterm_id;
	
	private String inst_no;
	
	private String inst_days;
	
	private String inst_percent;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;
	
	

	public String getPayterm_id() {
		return payterm_id;
	}

	public void setPayterm_id(String payterm_id) {
		this.payterm_id = payterm_id;
	}

	public String getPayterm_code() {
		return payterm_code;
	}

	public void setPayterm_code(String payterm_code) {
		this.payterm_code = payterm_code;
	}

	public String getInst_no() {
		return inst_no;
	}

	public void setInst_no(String inst_no) {
		this.inst_no = inst_no;
	}

	public String getInst_days() {
		return inst_days;
	}

	public void setInst_days(String inst_days) {
		this.inst_days = inst_days;
	}

	public String getInst_percent() {
		return inst_percent;
	}

	public void setInst_percent(String inst_percent) {
		this.inst_percent = inst_percent;
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

	public LocalDateTime getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(LocalDateTime deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}
	
	
}
