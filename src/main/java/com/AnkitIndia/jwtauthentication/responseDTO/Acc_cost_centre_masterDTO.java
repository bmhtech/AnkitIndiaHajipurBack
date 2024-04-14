package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.validation.constraints.Size;

public class Acc_cost_centre_masterDTO {
	
	@Size(max = 50)
	private String costcat_code;
	
	@Size(max = 100)
	private String costcentre_name;
	
	



	public String getCostcat_code() {
		return costcat_code;
	}

	public void setCostcat_code(String costcat_code) {
		this.costcat_code = costcat_code;
	}

	public String getCostcentre_name() {
		return costcentre_name;
	}

	public void setCostcentre_name(String costcentre_name) {
		this.costcentre_name = costcentre_name;
	}


	
}
