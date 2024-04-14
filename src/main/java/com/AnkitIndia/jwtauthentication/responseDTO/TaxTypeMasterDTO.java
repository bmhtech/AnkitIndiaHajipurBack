package com.AnkitIndia.jwtauthentication.responseDTO;

public class TaxTypeMasterDTO {
	
	private String taxtype_code;
	
	private String taxtype_name;
	
	private boolean taxtype_active;

	public String getTaxtype_code() {
		return taxtype_code;
	}

	public void setTaxtype_code(String taxtype_code) {
		this.taxtype_code = taxtype_code;
	}

	public String getTaxtype_name() {
		return taxtype_name;
	}

	public void setTaxtype_name(String taxtype_name) {
		this.taxtype_name = taxtype_name;
	}

	public boolean isTaxtype_active() {
		return taxtype_active;
	}

	public void setTaxtype_active(boolean taxtype_active) {
		this.taxtype_active = taxtype_active;
	}
	
	
	

}
