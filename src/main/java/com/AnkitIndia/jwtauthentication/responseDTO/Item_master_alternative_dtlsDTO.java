package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Item_master_alternative_dtlsDTO {

	private Long id;
	
	private String item_id;
	
	private String item_id_old;
	
	private String item_name;
	
	private String item_group;
	
	private String item_category;
	
	private String mstock_unit;
	
	private String hsn_code;
	
	private String addless;
	
	private double packing_cost;
	
	private String company_id;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	//Converting Name
	private String group_name;
	
	private String category_name;
	
	private String uom_name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_id_old() {
		return item_id_old;
	}

	public void setItem_id_old(String item_id_old) {
		this.item_id_old = item_id_old;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_group() {
		return item_group;
	}

	public void setItem_group(String item_group) {
		this.item_group = item_group;
	}

	public String getItem_category() {
		return item_category;
	}

	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}

	public String getMstock_unit() {
		return mstock_unit;
	}

	public void setMstock_unit(String mstock_unit) {
		this.mstock_unit = mstock_unit;
	}

	public String getHsn_code() {
		return hsn_code;
	}

	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
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

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getUom_name() {
		return uom_name;
	}

	public void setUom_name(String uom_name) {
		this.uom_name = uom_name;
	}

	public String getAddless() {
		return addless;
	}

	public void setAddless(String addless) {
		this.addless = addless;
	}

	public double getPacking_cost() {
		return packing_cost;
	}

	public void setPacking_cost(double packing_cost) {
		this.packing_cost = packing_cost;
	}
	
}
