package com.AnkitIndia.jwtauthentication.responseDTO;

public class Acc_taxcode_details_masterDTO {
	
	private String tax_code;
	
	private String tax_name;
	
	private boolean  taxcode_active;
	
	private String tax_description;

	public String getTax_code() {
		return tax_code;
	}

	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}

	public String getTax_name() {
		return tax_name;
	}

	public void setTax_name(String tax_name) {
		this.tax_name = tax_name;
	}

	public boolean isTaxcode_active() {
		return taxcode_active;
	}

	public void setTaxcode_active(boolean taxcode_active) {
		this.taxcode_active = taxcode_active;
	}

	public String getTax_description() {
		return tax_description;
	}

	public void setTax_description(String tax_description) {
		this.tax_description = tax_description;
	}
	
		
}
