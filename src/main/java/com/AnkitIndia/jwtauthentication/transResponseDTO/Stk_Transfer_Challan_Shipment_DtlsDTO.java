package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

public class Stk_Transfer_Challan_Shipment_DtlsDTO {

	private Long id;
	
	private String stk_challan_id;
	
	private String stk_challan_no;
	
	private String ship_addr;
	
	private String ship_details;
	
	private String pay_addr;
	
	private String pay_details;
	
	private String company_id;
	
	private String fin_year;
	
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

	public String getStk_challan_id() {
		return stk_challan_id;
	}

	public void setStk_challan_id(String stk_challan_id) {
		this.stk_challan_id = stk_challan_id;
	}

	public String getStk_challan_no() {
		return stk_challan_no;
	}

	public void setStk_challan_no(String stk_challan_no) {
		this.stk_challan_no = stk_challan_no;
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
	
	
}
