package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Wm_unload_advice_terms_conDTO {
	
	private String unadviceid;
	
	private String unadviceno;
	
	private String company_id;
	
	private String payment_mode;
	
	private String payment_terms;
	
	private String bank_name;
	
	private String account_name;
	
	private Long account_no;
	
	private String branch;
	
	private String iban;
	
	private String bic_swift_code;
	
	private double cash_limit;
	
	private String ifsc;
	
	private Long mobile;
	
	private String tcs_applicable;
	
	private double tcs_rate;

	
	public double getCash_limit() {
		return cash_limit;
	}

	public void setCash_limit(double cash_limit) {
		this.cash_limit = cash_limit;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getTcs_applicable() {
		return tcs_applicable;
	}

	public void setTcs_applicable(String tcs_applicable) {
		this.tcs_applicable = tcs_applicable;
	}

	public double getTcs_rate() {
		return tcs_rate;
	}

	public void setTcs_rate(double tcs_rate) {
		this.tcs_rate = tcs_rate;
	}

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

	public String getPayment_mode() {
		return payment_mode;
	}

	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}

	public String getPayment_terms() {
		return payment_terms;
	}

	public void setPayment_terms(String payment_terms) {
		this.payment_terms = payment_terms;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public Long getAccount_no() {
		return account_no;
	}

	public void setAccount_no(Long account_no) {
		this.account_no = account_no;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic_swift_code() {
		return bic_swift_code;
	}

	public void setBic_swift_code(String bic_swift_code) {
		this.bic_swift_code = bic_swift_code;
	}
	

}
