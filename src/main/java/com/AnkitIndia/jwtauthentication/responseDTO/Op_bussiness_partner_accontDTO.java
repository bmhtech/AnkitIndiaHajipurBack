package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Op_bussiness_partner_accontDTO {

	private String bp_Id;
	
	private String company_id;
	
	private String pay_cont_acc;
	
	private String adv_pay_acc;
	
	private String pay_term;
	
	private double discount;
	
	private double credit_lim;
	
	private Long credit_days;
	
	private boolean  cash_lim_status;
	
	private String cash_limit;

	public String getBp_Id() {
		return bp_Id;
	}

	public void setBp_Id(String bp_Id) {
		this.bp_Id = bp_Id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getPay_cont_acc() {
		return pay_cont_acc;
	}

	public void setPay_cont_acc(String pay_cont_acc) {
		this.pay_cont_acc = pay_cont_acc;
	}

	public String getAdv_pay_acc() {
		return adv_pay_acc;
	}

	public void setAdv_pay_acc(String adv_pay_acc) {
		this.adv_pay_acc = adv_pay_acc;
	}

	public String getPay_term() {
		return pay_term;
	}

	public void setPay_term(String pay_term) {
		this.pay_term = pay_term;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getCredit_lim() {
		return credit_lim;
	}

	public void setCredit_lim(double credit_lim) {
		this.credit_lim = credit_lim;
	}

	public Long getCredit_days() {
		return credit_days;
	}

	public void setCredit_days(Long credit_days) {
		this.credit_days = credit_days;
	}

	public boolean isCash_lim_status() {
		return cash_lim_status;
	}

	public void setCash_lim_status(boolean cash_lim_status) {
		this.cash_lim_status = cash_lim_status;
	}

	public String getCash_limit() {
		return cash_limit;
	}

	public void setCash_limit(String cash_limit) {
		this.cash_limit = cash_limit;
	}
	
}
