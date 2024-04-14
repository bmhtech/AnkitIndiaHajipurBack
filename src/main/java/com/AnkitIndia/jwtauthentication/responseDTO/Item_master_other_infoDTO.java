package com.AnkitIndia.jwtauthentication.responseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

public class Item_master_other_infoDTO {
	
	private Long id;
	
	private String item_id;
	
	private String item_code;
	
	private String company_id;
	
	private String gen_name;
	
	private String self_life;
	
	private Date exp_date;
	
	private String specific_desc;
	
	private String ser_item;
	
	private String non_store_item;
	
	private String stock_item;
	
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

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getGen_name() {
		return gen_name;
	}

	public void setGen_name(String gen_name) {
		this.gen_name = gen_name;
	}

	public String getSelf_life() {
		return self_life;
	}

	public void setSelf_life(String self_life) {
		this.self_life = self_life;
	}

	public Date getExp_date() {
		return exp_date;
	}

	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
	}

	public String getSpecific_desc() {
		return specific_desc;
	}

	public void setSpecific_desc(String specific_desc) {
		this.specific_desc = specific_desc;
	}

	public String getSer_item() {
		return ser_item;
	}

	public void setSer_item(String ser_item) {
		this.ser_item = ser_item;
	}

	public String getNon_store_item() {
		return non_store_item;
	}

	public void setNon_store_item(String non_store_item) {
		this.non_store_item = non_store_item;
	}

	public String getStock_item() {
		return stock_item;
	}

	public void setStock_item(String stock_item) {
		this.stock_item = stock_item;
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
