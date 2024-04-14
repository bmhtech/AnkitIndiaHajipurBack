package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Item_group_master_sales_accDTO {

	private Long id;
	
	private String item_group_code;
	
	private String item_group_id;
	
	private String company_id;

	private String item_total;
	
	private String discount;

	//private String net_total;
	
	//private String total_bill_amt;
	
	private String adjplus;
	
	private String adjminus;
	
	//private String final_bill_amt;
	
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

	public String getItem_group_code() {
		return item_group_code;
	}

	public void setItem_group_code(String item_group_code) {
		this.item_group_code = item_group_code;
	}

	public String getItem_group_id() {
		return item_group_id;
	}

	public void setItem_group_id(String item_group_id) {
		this.item_group_id = item_group_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getItem_total() {
		return item_total;
	}

	public void setItem_total(String item_total) {
		this.item_total = item_total;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getAdjplus() {
		return adjplus;
	}

	public void setAdjplus(String adjplus) {
		this.adjplus = adjplus;
	}

	public String getAdjminus() {
		return adjminus;
	}

	public void setAdjminus(String adjminus) {
		this.adjminus = adjminus;
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
