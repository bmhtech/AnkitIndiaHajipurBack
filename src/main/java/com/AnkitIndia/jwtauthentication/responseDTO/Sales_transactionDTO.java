package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Sales_transactionDTO {

	private Long id;
	
	private String business_unit;
	
	private String item_id;
		
	private String packing_id;
	
	private double sales_item_qty;
	
	private double sales_pack_qty;
	
	private double load_item_qty;
	
	private double load_pack_qty;
	
	private double delv_item_qty;
	
	private double delv_pack_qty;
	
	private String packing_item;
	
	private String reference_id;
	
	private String fin_year;
	
	private String company_id;
	
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

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getPacking_id() {
		return packing_id;
	}

	public void setPacking_id(String packing_id) {
		this.packing_id = packing_id;
	}

	public double getSales_item_qty() {
		return sales_item_qty;
	}

	public void setSales_item_qty(double sales_item_qty) {
		this.sales_item_qty = sales_item_qty;
	}

	public double getSales_pack_qty() {
		return sales_pack_qty;
	}

	public void setSales_pack_qty(double sales_pack_qty) {
		this.sales_pack_qty = sales_pack_qty;
	}

	public double getLoad_item_qty() {
		return load_item_qty;
	}

	public void setLoad_item_qty(double load_item_qty) {
		this.load_item_qty = load_item_qty;
	}

	public double getLoad_pack_qty() {
		return load_pack_qty;
	}

	public void setLoad_pack_qty(double load_pack_qty) {
		this.load_pack_qty = load_pack_qty;
	}

	public double getDelv_item_qty() {
		return delv_item_qty;
	}

	public void setDelv_item_qty(double delv_item_qty) {
		this.delv_item_qty = delv_item_qty;
	}

	public double getDelv_pack_qty() {
		return delv_pack_qty;
	}

	public void setDelv_pack_qty(double delv_pack_qty) {
		this.delv_pack_qty = delv_pack_qty;
	}

	public String getPacking_item() {
		return packing_item;
	}

	public void setPacking_item(String packing_item) {
		this.packing_item = packing_item;
	}

	public String getReference_id() {
		return reference_id;
	}

	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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
