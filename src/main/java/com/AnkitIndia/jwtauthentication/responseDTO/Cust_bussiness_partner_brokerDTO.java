package com.AnkitIndia.jwtauthentication.responseDTO;

import java.sql.Date;

public class Cust_bussiness_partner_brokerDTO {
	
	private String cp_Id;
	
	private String company_id;
	
	private Long sl_no;  
	
	private String broker_code;
	
	private String broker_name;
	
	private String ven_code_name;
	
	private String ven_name;
	
	private String basis;
	
	private String based_on;
	
	private double rate;
	
	/* New */
	private String brokerage_acc;
	
	private double tds_rate;
	
	private String tds_acc;
	
	private Date eff_date;
	
	private String remarks;

	public String getCp_Id() {
		return cp_Id;
	}

	public void setCp_Id(String cp_Id) {
		this.cp_Id = cp_Id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public Long getSl_no() {
		return sl_no;
	}

	public void setSl_no(Long sl_no) {
		this.sl_no = sl_no;
	}

	public String getBroker_code() {
		return broker_code;
	}

	public void setBroker_code(String broker_code) {
		this.broker_code = broker_code;
	}

	public String getBroker_name() {
		return broker_name;
	}

	public void setBroker_name(String broker_name) {
		this.broker_name = broker_name;
	}

	public String getVen_code_name() {
		return ven_code_name;
	}

	public void setVen_code_name(String ven_code_name) {
		this.ven_code_name = ven_code_name;
	}

	public String getVen_name() {
		return ven_name;
	}

	public void setVen_name(String ven_name) {
		this.ven_name = ven_name;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}
	
	public String getBased_on() {
		return based_on;
	}

	public void setBased_on(String based_on) {
		this.based_on = based_on;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getBrokerage_acc() {
		return brokerage_acc;
	}

	public void setBrokerage_acc(String brokerage_acc) {
		this.brokerage_acc = brokerage_acc;
	}

	public double getTds_rate() {
		return tds_rate;
	}

	public void setTds_rate(double tds_rate) {
		this.tds_rate = tds_rate;
	}

	public String getTds_acc() {
		return tds_acc;
	}

	public void setTds_acc(String tds_acc) {
		this.tds_acc = tds_acc;
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

	public Cust_bussiness_partner_brokerDTO() {
		super();
	}

	public Cust_bussiness_partner_brokerDTO(String cp_Id, String company_id, Long sl_no, String broker_code,
			String broker_name, String ven_code_name, String ven_name, String basis, String based_on, double rate,
			String brokerage_acc, double tds_rate, String tds_acc, String remarks) {
		super();
		this.cp_Id = cp_Id;
		this.company_id = company_id;
		this.sl_no = sl_no;
		this.broker_code = broker_code;
		this.broker_name = broker_name;
		this.ven_code_name = ven_code_name;
		this.ven_name = ven_name;
		this.basis = basis;
		this.based_on = based_on;
		this.rate = rate;
		this.brokerage_acc = brokerage_acc;
		this.tds_rate = tds_rate;
		this.tds_acc = tds_acc;
		this.remarks = remarks;
	}
	
	

}
