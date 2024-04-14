package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Pur_Bill_Bp_DetailsDTO {
	
	private String company_id;
	
	private String pur_bill_id;
	
	private String pur_bill_no;
	
	private String supp_name;
	
    private long supp_phone;
    
	private String supp_fax;
    
	private String supp_email;
    
	private String supp_address;
    
	private String cp_designation;
    
	private String cp_name;
    
    private long cp_phone;
    
	private String cp_fax;
    
	private String cp_email;
    
	private String cp_address;
	
	private String fin_year;

	private String  modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getPur_bill_id() {
		return pur_bill_id;
	}

	public void setPur_bill_id(String pur_bill_id) {
		this.pur_bill_id = pur_bill_id;
	}

	public String getPur_bill_no() {
		return pur_bill_no;
	}

	public void setPur_bill_no(String pur_bill_no) {
		this.pur_bill_no = pur_bill_no;
	}

	public String getSupp_name() {
		return supp_name;
	}

	public void setSupp_name(String supp_name) {
		this.supp_name = supp_name;
	}

	public long getSupp_phone() {
		return supp_phone;
	}

	public void setSupp_phone(long supp_phone) {
		this.supp_phone = supp_phone;
	}

	public String getSupp_fax() {
		return supp_fax;
	}

	public void setSupp_fax(String supp_fax) {
		this.supp_fax = supp_fax;
	}

	public String getSupp_email() {
		return supp_email;
	}

	public void setSupp_email(String supp_email) {
		this.supp_email = supp_email;
	}

	public String getSupp_address() {
		return supp_address;
	}

	public void setSupp_address(String supp_address) {
		this.supp_address = supp_address;
	}

	public String getCp_designation() {
		return cp_designation;
	}

	public void setCp_designation(String cp_designation) {
		this.cp_designation = cp_designation;
	}

	public String getCp_name() {
		return cp_name;
	}

	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}

	public long getCp_phone() {
		return cp_phone;
	}

	public void setCp_phone(long cp_phone) {
		this.cp_phone = cp_phone;
	}

	public String getCp_fax() {
		return cp_fax;
	}

	public void setCp_fax(String cp_fax) {
		this.cp_fax = cp_fax;
	}

	public String getCp_email() {
		return cp_email;
	}

	public void setCp_email(String cp_email) {
		this.cp_email = cp_email;
	}

	public String getCp_address() {
		return cp_address;
	}

	public void setCp_address(String cp_address) {
		this.cp_address = cp_address;
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
