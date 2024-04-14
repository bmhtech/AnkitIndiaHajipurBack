package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Pur_return_note_broker_dtlsDTO {
	
	private String purreturnnoteid;
	 
	 private String purreturnnoteno;
	 
	 private int slno;
	 
	 private String brokercode;
	 
	 private String basis;
	 
	 private double rate;

	 private String company_id;
	    
	 private String fin_year;
		
	 private String modified_type;
		
	 private LocalDateTime inserted_on;
		
	 private String inserted_by;
		
	 private LocalDateTime updated_on;
		
	 private String updated_by;
		
	 private LocalDateTime deleted_on;
		
	 private String deleted_by;

	public String getPurreturnnoteid() {
		return purreturnnoteid;
	}

	public void setPurreturnnoteid(String purreturnnoteid) {
		this.purreturnnoteid = purreturnnoteid;
	}

	public String getPurreturnnoteno() {
		return purreturnnoteno;
	}

	public void setPurreturnnoteno(String purreturnnoteno) {
		this.purreturnnoteno = purreturnnoteno;
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
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
