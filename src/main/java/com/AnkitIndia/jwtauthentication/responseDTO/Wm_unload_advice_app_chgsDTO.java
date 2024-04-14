package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Wm_unload_advice_app_chgsDTO {

	private String unadviceid;
	
	private String unadviceno;
	
	private String company_id;
	
	private String charges_name;
	
	private String rate_cal_method;
	
	private double tax_rate;
	
	private double amount;

	public String getUnadviceid() {
		return unadviceid;
	}

	public void setUnadviceid(String unadviceid) {
		this.unadviceid = unadviceid;
	}

	public String getUnadviceno() {
		return unadviceno;
	}

	public void setUnadviceno(String unadviceno) {
		this.unadviceno = unadviceno;
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

	public String getRate_cal_method() {
		return rate_cal_method;
	}

	public void setRate_cal_method(String rate_cal_method) {
		this.rate_cal_method = rate_cal_method;
	}

	public double getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(double tax_rate) {
		this.tax_rate = tax_rate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
