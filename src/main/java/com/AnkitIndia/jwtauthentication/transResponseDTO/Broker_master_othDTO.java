package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Broker_master_othDTO {

	private String broker_Id;
	
	private String company_id;
	
	private String broker_code;
	
	private Long srl_no;
	
	private String oth_code_name;
	
	private String oth_name; 
	
	private String basis;
	
	private double rate;
	
	private Date eff_date;
	
	private String remarks;

	public String getBroker_Id() {
		return broker_Id;
	}

	public void setBroker_Id(String broker_Id) {
		this.broker_Id = broker_Id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getBroker_code() {
		return broker_code;
	}

	public void setBroker_code(String broker_code) {
		this.broker_code = broker_code;
	}

	

	public Long getSrl_no() {
		return srl_no;
	}

	public void setSrl_no(Long srl_no) {
		this.srl_no = srl_no;
	}

	public String getOth_code_name() {
		return oth_code_name;
	}

	public void setOth_code_name(String oth_code_name) {
		this.oth_code_name = oth_code_name;
	}

	public String getOth_name() {
		return oth_name;
	}

	public void setOth_name(String oth_name) {
		this.oth_name = oth_name;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Date getEff_date() {
		return eff_date;
	}

	public void setEff_date(Date eff_date) {
		this.eff_date = eff_date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
