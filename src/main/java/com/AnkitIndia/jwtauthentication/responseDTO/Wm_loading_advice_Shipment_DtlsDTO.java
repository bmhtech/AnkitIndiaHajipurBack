package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Wm_loading_advice_Shipment_DtlsDTO {

	private String advice_id;
	
	private String company_id;
	
	private String advice_no;
	
	private String ship_addr;
    
	private String ship_addr_code;
	
	private String ship_details;
    
	private String pay_addr;
    
	private String pay_details;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;

	public String getShip_addr_code() {
		return ship_addr_code;
	}

	public void setShip_addr_code(String ship_addr_code) {
		this.ship_addr_code = ship_addr_code;
	}

	public String getAdvice_id() {
		return advice_id;
	}

	public void setAdvice_id(String advice_id) {
		this.advice_id = advice_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getAdvice_no() {
		return advice_no;
	}

	public void setAdvice_no(String advice_no) {
		this.advice_no = advice_no;
	}

	public String getShip_addr() {
		return ship_addr;
	}

	public void setShip_addr(String ship_addr) {
		this.ship_addr = ship_addr;
	}

	public String getShip_details() {
		return ship_details;
	}

	public void setShip_details(String ship_details) {
		this.ship_details = ship_details;
	}

	public String getPay_addr() {
		return pay_addr;
	}

	public void setPay_addr(String pay_addr) {
		this.pay_addr = pay_addr;
	}

	public String getPay_details() {
		return pay_details;
	}

	public void setPay_details(String pay_details) {
		this.pay_details = pay_details;
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
