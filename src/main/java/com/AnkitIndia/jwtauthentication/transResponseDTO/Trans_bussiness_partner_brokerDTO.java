package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Size;


public class Trans_bussiness_partner_brokerDTO {
	
	private Long id;
	
	private String bp_Id;
	
	private String bp_code;
	
	private String company_id;
	
	private int sl_no;
	
	private String ven_code_name;
	
	private String ven_name;
	
	private String basis;
	
	private String based_on;
	
	private String rate;
	
	private String brokerage_acc;
	
	private double tds_rate;
	
	private String tds_acc;
	
	private String eff_date;
	
	private String remarks;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBp_Id() {
		return bp_Id;
	}

	public void setBp_Id(String bp_Id) {
		this.bp_Id = bp_Id;
	}

	public String getBp_code() {
		return bp_code;
	}

	public void setBp_code(String bp_code) {
		this.bp_code = bp_code;
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

	public String getBased_on() {
		return based_on;
	}

	public void setBased_on(String based_on) {
		this.based_on = based_on;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
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

	public String getEff_date() {
		return eff_date;
	}

	public void setEff_date(String eff_date) {
		this.eff_date = eff_date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}

	public LocalDateTime getInserted_on() {
		return inserted_on;
	}

	public void setInserted_on(LocalDateTime inserted_on) {
		this.inserted_on = inserted_on;
	}

	public String getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}

	public LocalDateTime getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	
	
	
}
