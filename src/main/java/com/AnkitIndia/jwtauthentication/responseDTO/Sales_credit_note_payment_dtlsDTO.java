package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Sales_credit_note_payment_dtlsDTO {

	private String creditnoteid;
	
	private String creditnoteno;
	
	private String mode_of_payment;
	
	private String payment_term;
	
	private String bank_name;

	private String account_name;
	
	private String account_no; 
	
	private String branch; 
	
	private String iban; 
    
	private String bic_swift_code;
    
	private double adv_payment;
    
	private String ifsc_code;
    
	private double cash_limit;
    
    private Long mobile;
	
	private String company_id;
    
	private String fin_year;
    
	private String username;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;

	public String getCreditnoteid() {
		return creditnoteid;
	}

	public void setCreditnoteid(String creditnoteid) {
		this.creditnoteid = creditnoteid;
	}

	public String getCreditnoteno() {
		return creditnoteno;
	}

	public void setCreditnoteno(String creditnoteno) {
		this.creditnoteno = creditnoteno;
	}

	public String getMode_of_payment() {
		return mode_of_payment;
	}

	public void setMode_of_payment(String mode_of_payment) {
		this.mode_of_payment = mode_of_payment;
	}

	public String getPayment_term() {
		return payment_term;
	}

	public void setPayment_term(String payment_term) {
		this.payment_term = payment_term;
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

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
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

	public double getAdv_payment() {
		return adv_payment;
	}

	public void setAdv_payment(double adv_payment) {
		this.adv_payment = adv_payment;
	}

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public double getCash_limit() {
		return cash_limit;
	}

	public void setCash_limit(double cash_limit) {
		this.cash_limit = cash_limit;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public LocalDateTime getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(LocalDateTime deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}
	
	
	
}
