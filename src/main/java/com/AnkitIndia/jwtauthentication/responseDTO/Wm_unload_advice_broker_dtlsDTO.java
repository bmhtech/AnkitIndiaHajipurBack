package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Wm_unload_advice_broker_dtlsDTO {
	
	private String unadviceid;
	
	private String unadviceno;
	
	private String company_id;
	
	private int sl_no;
	
	private String ven_code_name;
	
	private String ven_name;
	
	private String basis;
	
	private double rate;
	
	private String brokerage_acc;
	
	private double tds_rate;
	
	private String tds_acc;

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

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
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
	

}
