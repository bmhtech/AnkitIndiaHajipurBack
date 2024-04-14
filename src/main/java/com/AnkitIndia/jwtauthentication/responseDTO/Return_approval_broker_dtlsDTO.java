package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Return_approval_broker_dtlsDTO {

	private Long id;
	
	private String salesreturnid;
	
	private String salesreturnno;
	
	private Long slno;
	  
	private String brokercode;
	 
	private String basis;
    
	private double rate;
	
	private String company_id;
	    
	private String fin_year;
	    
	private String username;
		
	private String modified_type;
		
	private LocalDateTime inserted_on;
		
	private String inserted_by;
		
	private LocalDateTime updated_on;
	
	private String updated_by;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSalesreturnid() {
		return salesreturnid;
	}

	public void setSalesreturnid(String salesreturnid) {
		this.salesreturnid = salesreturnid;
	}

	public String getSalesreturnno() {
		return salesreturnno;
	}

	public void setSalesreturnno(String salesreturnno) {
		this.salesreturnno = salesreturnno;
	}

	public Long getSlno() {
		return slno;
	}

	public void setSlno(Long slno) {
		this.slno = slno;
	}

	public String getBrokercode() {
		return brokercode;
	}

	public void setBrokercode(String brokercode) {
		this.brokercode = brokercode;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
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

	
}
