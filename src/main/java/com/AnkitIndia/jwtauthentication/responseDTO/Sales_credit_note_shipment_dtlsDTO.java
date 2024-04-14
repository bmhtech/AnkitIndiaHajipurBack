package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Sales_credit_note_shipment_dtlsDTO {
	
	private String creditnoteid;
	
	private String creditnoteno;
	
	private String shipaddr;
	
	private String shipdtls;
	
	private String paytoaddr;
	
	private String paytodtls;
	
	private String company_id;
    
	private String fin_year;
    
	private String username;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;

	public String getCreditnoteid() {
		return creditnoteid;
	}

	public void setCreditnoteid(String creditnoteid) {
		this.creditnoteid = creditnoteid;
	}

	public String getCreditnoteno() {
		return creditnoteno;
	}

	public void setCreditnoteno(String creditnoteno) {
		this.creditnoteno = creditnoteno;
	}

	public String getShipaddr() {
		return shipaddr;
	}

	public void setShipaddr(String shipaddr) {
		this.shipaddr = shipaddr;
	}

	public String getShipdtls() {
		return shipdtls;
	}

	public void setShipdtls(String shipdtls) {
		this.shipdtls = shipdtls;
	}

	public String getPaytoaddr() {
		return paytoaddr;
	}

	public void setPaytoaddr(String paytoaddr) {
		this.paytoaddr = paytoaddr;
	}

	public String getPaytodtls() {
		return paytodtls;
	}

	public void setPaytodtls(String paytodtls) {
		this.paytodtls = paytodtls;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
