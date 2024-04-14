package com.AnkitIndia.jwtauthentication.responseDTO;


public class Charge_MasterdtlsDTO {
	
	private String charge_id;
	
	private String charge_name; 
	
	private String charge_ac;
	
	private String rate_cal;
	
	private String method;
	
	private String tax_code;
	
	private double tax_rate;
	
	private String required;
	
	private double app_rate;
	
	
	

	public double getApp_rate() {
		return app_rate;
	}

	public void setApp_rate(double app_rate) {
		this.app_rate = app_rate;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getCharge_id() {
		return charge_id;
	}

	public void setCharge_id(String charge_id) {
		this.charge_id = charge_id;
	}

	public String getCharge_name() {
		return charge_name;
	}

	public void setCharge_name(String charge_name) {
		this.charge_name = charge_name;
	}

	public String getCharge_ac() {
		return charge_ac;
	}

	public void setCharge_ac(String charge_ac) {
		this.charge_ac = charge_ac;
	}

	public String getRate_cal() {
		return rate_cal;
	}

	public void setRate_cal(String rate_cal) {
		this.rate_cal = rate_cal;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTax_code() {
		return tax_code;
	}

	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}

	public double getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(double tax_rate) {
		this.tax_rate = tax_rate;
	}

	
}