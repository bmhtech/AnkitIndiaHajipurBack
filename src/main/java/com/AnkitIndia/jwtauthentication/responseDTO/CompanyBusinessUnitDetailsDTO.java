package com.AnkitIndia.jwtauthentication.responseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

public class CompanyBusinessUnitDetailsDTO {

	
private Date expiry_date;
	
	private String licence_name; 
	private String licence_no;
	private String remarks;
	private Long sln_no;
	private String businessunit_id; 
	
    private String modified_type;
	
	private String inserted_by;
	
	private LocalDateTime inserted_on;
	
	private String updated_by;
	
	private LocalDateTime updated_on;
	
	private String deleted_by;
	
	private LocalDateTime deleted_on;

	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getLicence_name() {
		return licence_name;
	}

	public void setLicence_name(String licence_name) {
		this.licence_name = licence_name;
	}

	public String getLicence_no() {
		return licence_no;
	}

	public void setLicence_no(String licence_no) {
		this.licence_no = licence_no;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getSln_no() {
		return sln_no;
	}

	public void setSln_no(Long sln_no) {
		this.sln_no = sln_no;
	}

	public String getBusinessunit_id() {
		return businessunit_id;
	}

	public void setBusinessunit_id(String businessunit_id) {
		this.businessunit_id = businessunit_id;
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

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public LocalDateTime getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(LocalDateTime deleted_on) {
		this.deleted_on = deleted_on;
	}
	
}
