package com.AnkitIndia.jwtauthentication.transResponseDTO;

public class Broker_master_accountDTO {

	private String broker_Id;
	
	private String broker_code;
	
	private String company_id;
	
	private String pay_cont_acc;
	
	private String pref_bank_acc;
	
	private String pay_term;
	
	private Long credit_lim;
	
	private Long cash_lim;
	
	private boolean cash_lim_active;
	
	private String pay_mode;
	
	private String acc_holder_name;
	
	private String acc_type;
	
	private String acc_remarks;
	
	private String acc_no;
	
	private String bank_name;
	
	private String ifsc_code;
	
	private Long mobile;

	public String getBroker_Id() {
		return broker_Id;
	}

	public void setBroker_Id(String broker_Id) {
		this.broker_Id = broker_Id;
	}

	public String getBroker_code() {
		return broker_code;
	}

	public void setBroker_code(String broker_code) {
		this.broker_code = broker_code;
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

	public String getPref_bank_acc() {
		return pref_bank_acc;
	}

	public void setPref_bank_acc(String pref_bank_acc) {
		this.pref_bank_acc = pref_bank_acc;
	}

	public String getPay_term() {
		return pay_term;
	}

	public void setPay_term(String pay_term) {
		this.pay_term = pay_term;
	}

	public Long getCredit_lim() {
		return credit_lim;
	}

	public void setCredit_lim(Long credit_lim) {
		this.credit_lim = credit_lim;
	}

	public Long getCash_lim() {
		return cash_lim;
	}

	public void setCash_lim(Long cash_lim) {
		this.cash_lim = cash_lim;
	}

	public boolean isCash_lim_active() {
		return cash_lim_active;
	}

	public void setCash_lim_active(boolean cash_lim_active) {
		this.cash_lim_active = cash_lim_active;
	}

	public String getPay_mode() {
		return pay_mode;
	}

	public void setPay_mode(String pay_mode) {
		this.pay_mode = pay_mode;
	}

	public String getAcc_holder_name() {
		return acc_holder_name;
	}

	public void setAcc_holder_name(String acc_holder_name) {
		this.acc_holder_name = acc_holder_name;
	}
	
	public String getAcc_type() {
		return acc_type;
	}

	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}

	public String getAcc_remarks() {
		return acc_remarks;
	}

	public void setAcc_remarks(String acc_remarks) {
		this.acc_remarks = acc_remarks;
	}

	public String getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	
}
