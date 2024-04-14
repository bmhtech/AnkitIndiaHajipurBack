package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.validation.constraints.Size;

public class Broker_templtDTO {
	@Size(max = 50)
	private String templt_code;
	
	@Size(max = 50)
	private String templt_name;

	public String getTemplt_code() {
		return templt_code;
	}

	public void setTemplt_code(String templt_code) {
		this.templt_code = templt_code;
	}

	public String getTemplt_name() {
		return templt_name;
	}

	public void setTemplt_name(String templt_name) {
		this.templt_name = templt_name;
	}
	
	
}
