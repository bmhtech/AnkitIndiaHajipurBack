package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Pur_Order_app_chgsDTO {
	
	private Long id;
	
	private String pur_orderid;

	private String pur_order_no;

	private String company_id;

	private String charges_name;
	
	private String add_less; /* New */
	
	private String rate_cal_method;
	
	private double app_rate; /* New */
	
	private double amount;
	
	private double tax_rate;
	
	private String fin_year;

	private String  modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private String required;
	
	

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getPur_orderid() {
		return pur_orderid;
	}

	public void setPur_orderid(String pur_orderid) {
		this.pur_orderid = pur_orderid;
	}

	public String getPur_order_no() {
		return pur_order_no;
	}

	public void setPur_order_no(String pur_order_no) {
		this.pur_order_no = pur_order_no;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getCharges_name() {
		return charges_name;
	}

	public void setCharges_name(String charges_name) {
		this.charges_name = charges_name;
	}

	public String getAdd_less() {
		return add_less;
	}

	public void setAdd_less(String add_less) {
		this.add_less = add_less;
	}

	public String getRate_cal_method() {
		return rate_cal_method;
	}

	public void setRate_cal_method(String rate_cal_method) {
		this.rate_cal_method = rate_cal_method;
	}

	public double getApp_rate() {
		return app_rate;
	}

	public void setApp_rate(double app_rate) {
		this.app_rate = app_rate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(double tax_rate) {
		this.tax_rate = tax_rate;
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
