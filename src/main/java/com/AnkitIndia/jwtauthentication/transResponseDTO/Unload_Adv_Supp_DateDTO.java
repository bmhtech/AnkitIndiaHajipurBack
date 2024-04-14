package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.util.Date;

import javax.validation.constraints.Size;

public class Unload_Adv_Supp_DateDTO {
	
	@Size(max = 50)
	private String ul_Code;
	
	@Size(max = 50)
	private String Supp_Name;
	
	private Date ul_date;

	public String getUl_Code() {
		return ul_Code;
	}

	public void setUl_Code(String ul_Code) {
		this.ul_Code = ul_Code;
	}

	public String getSupp_Name() {
		return Supp_Name;
	}

	public void setSupp_Name(String supp_Name) {
		Supp_Name = supp_Name;
	}

	public Date getUl_date() {
		return ul_date;
	}

	public void setUl_date(Date ul_date) {
		this.ul_date = ul_date;
	}
	
	

}
