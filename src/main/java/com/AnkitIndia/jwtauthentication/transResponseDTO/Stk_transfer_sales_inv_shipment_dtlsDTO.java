package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;


public class Stk_transfer_sales_inv_shipment_dtlsDTO {
	private Long id;
	
	private String stk_sales_inv_id;
	
	private String stk_sales_inv_no;
	
	private String shipaddr;
	
	private String shipdtls;
	
	private String paytoaddr;
	
	private String paytodtls;
	
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

	public String getStk_sales_inv_id() {
		return stk_sales_inv_id;
	}

	public void setStk_sales_inv_id(String stk_sales_inv_id) {
		this.stk_sales_inv_id = stk_sales_inv_id;
	}

	public String getStk_sales_inv_no() {
		return stk_sales_inv_no;
	}

	public void setStk_sales_inv_no(String stk_sales_inv_no) {
		this.stk_sales_inv_no = stk_sales_inv_no;
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
