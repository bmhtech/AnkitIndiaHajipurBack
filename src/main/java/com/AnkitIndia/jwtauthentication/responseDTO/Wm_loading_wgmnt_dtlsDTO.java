package com.AnkitIndia.jwtauthentication.responseDTO;

import java.util.Date;

import javax.validation.constraints.Size;

public class Wm_loading_wgmnt_dtlsDTO {

	private String wgment_id;
	
	private String company_id;
	
	private int sl_no;
	
	private String customer;
	
	private String advice;
	
	private Date wgment_date;

	public String getWgment_id() {
		return wgment_id;
	}

	public void setWgment_id(String wgment_id) {
		this.wgment_id = wgment_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public Date getWgment_date() {
		return wgment_date;
	}

	public void setWgment_date(Date wgment_date) {
		this.wgment_date = wgment_date;
	}
	
}
