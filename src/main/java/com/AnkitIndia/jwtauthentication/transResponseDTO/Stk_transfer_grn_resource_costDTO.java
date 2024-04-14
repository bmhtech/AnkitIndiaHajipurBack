package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

public class Stk_transfer_grn_resource_costDTO {

	private Long id;
	
	private String stk_grn_id;
	
	private String stk_grn_no;
	
	private String charge_name;
	
	private String rate_cal_method;
	
	private double amount;
	
	private double tax_rate;
	
	private double tax_amt;
	
	private double gross_amt;
	
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

	public String getStk_grn_id() {
		return stk_grn_id;
	}

	public void setStk_grn_id(String stk_grn_id) {
		this.stk_grn_id = stk_grn_id;
	}

	public String getStk_grn_no() {
		return stk_grn_no;
	}

	public void setStk_grn_no(String stk_grn_no) {
		this.stk_grn_no = stk_grn_no;
	}

	public String getCharge_name() {
		return charge_name;
	}

	public void setCharge_name(String charge_name) {
		this.charge_name = charge_name;
	}

	public String getRate_cal_method() {
		return rate_cal_method;
	}

	public void setRate_cal_method(String rate_cal_method) {
		this.rate_cal_method = rate_cal_method;
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

	public double getTax_amt() {
		return tax_amt;
	}

	public void setTax_amt(double tax_amt) {
		this.tax_amt = tax_amt;
	}

	public double getGross_amt() {
		return gross_amt;
	}

	public void setGross_amt(double gross_amt) {
		this.gross_amt = gross_amt;
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
