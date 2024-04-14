package com.AnkitIndia.jwtauthentication.transResponseDTO;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Trans_bussiness_partner_accontDTO {
	
	private String bp_Id;
	
	private String bp_code;
	
	private String company_id;
	
	private String  pay_cont_acc;
	
	private String party_bank_acc;
	
	private String pay_term;
	
	private double credit_lim;
	
	private double cash_limit;
	
	private boolean  cash_lim_status;
	
	private String adv_pay;
	
	private String adv_pay_mode;
	
	private String mode_of_adv_pay;
	
	private String max_adv_vehi;

	private String acc_holder_name;
	
	private String acc_type;

	private String acc_remarks;
	
	private String acc_no;
	
	private String bank_name;
	
	private String ifsc_code;
	
	private Long mobile;
	
	private String tds_account;
	
	private String party_nature;
	
	private String default_tds_nature;
	
	private String tds_rate;
	
	private String mode_of_pay;
	
	private String tcs_applicable;
	
	private double tcs_rate;
	
	private String tcs_date;
	
	private String iban;
	
	private String bic_swift_code;
	
	private String branch;
	
	
	
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

	public String getPay_cont_acc() {
		return pay_cont_acc;
	}

	public void setPay_cont_acc(String pay_cont_acc) {
		this.pay_cont_acc = pay_cont_acc;
	}

	public String getParty_bank_acc() {
		return party_bank_acc;
	}

	public void setParty_bank_acc(String party_bank_acc) {
		this.party_bank_acc = party_bank_acc;
	}

	public String getPay_term() {
		return pay_term;
	}

	public void setPay_term(String pay_term) {
		this.pay_term = pay_term;
	}

	public double getCredit_lim() {
		return credit_lim;
	}

	public void setCredit_lim(double credit_lim) {
		this.credit_lim = credit_lim;
	}

	public double getCash_limit() {
		return cash_limit;
	}

	public void setCash_limit(double cash_limit) {
		this.cash_limit = cash_limit;
	}

	public boolean isCash_lim_status() {
		return cash_lim_status;
	}

	public void setCash_lim_status(boolean cash_lim_status) {
		this.cash_lim_status = cash_lim_status;
	}

	public String getAdv_pay() {
		return adv_pay;
	}

	public void setAdv_pay(String adv_pay) {
		this.adv_pay = adv_pay;
	}

	public String getAdv_pay_mode() {
		return adv_pay_mode;
	}

	public void setAdv_pay_mode(String adv_pay_mode) {
		this.adv_pay_mode = adv_pay_mode;
	}

	public String getMode_of_adv_pay() {
		return mode_of_adv_pay;
	}

	public void setMode_of_adv_pay(String mode_of_adv_pay) {
		this.mode_of_adv_pay = mode_of_adv_pay;
	}

	public String getMax_adv_vehi() {
		return max_adv_vehi;
	}

	public void setMax_adv_vehi(String max_adv_vehi) {
		this.max_adv_vehi = max_adv_vehi;
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

	public String getTds_account() {
		return tds_account;
	}

	public void setTds_account(String tds_account) {
		this.tds_account = tds_account;
	}

	public String getParty_nature() {
		return party_nature;
	}

	public void setParty_nature(String party_nature) {
		this.party_nature = party_nature;
	}

	public String getDefault_tds_nature() {
		return default_tds_nature;
	}

	public void setDefault_tds_nature(String default_tds_nature) {
		this.default_tds_nature = default_tds_nature;
	}

	public String getTds_rate() {
		return tds_rate;
	}

	public void setTds_rate(String tds_rate) {
		this.tds_rate = tds_rate;
	}

	public String getMode_of_pay() {
		return mode_of_pay;
	}

	public void setMode_of_pay(String mode_of_pay) {
		this.mode_of_pay = mode_of_pay;
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

	public String getTcs_date() {
		return tcs_date;
	}

	public void setTcs_date(String tcs_date) {
		this.tcs_date = tcs_date;
	}
	
	
}
