package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Pur_Order_Trans_InfoDTO {

	
	private String pur_order_no;
	
	private String trans_borne_by;
	
	private String mode_of_trans;
	
	private String transporter_name;
	
	private String transport_rate;
	
	private String charge_code;
	
	private double rate_value;
	
	private String payment_mode;
	
	private String payment_terms;
	
	private String bank_name;
	
	private String account_name;
	
	private Long account_no;
	
	private String branch;
	
	private String iban;
	
	private String bic_swift_code;
	
	private double cash_limit;
	
	private String ifsc_code;
	
	private Long mobile;

	private String transport_from;
	
	private String transport_to;
	
	public String getTransport_from() {
		return transport_from;
	}

	public void setTransport_from(String transport_from) {
		this.transport_from = transport_from;
	}

	public String getTransport_to() {
		return transport_to;
	}

	public void setTransport_to(String transport_to) {
		this.transport_to = transport_to;
	}

	public double getCash_limit() {
		return cash_limit;
	}

	public void setCash_limit(double cash_limit) {
		this.cash_limit = cash_limit;
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

	public String getPur_order_no() {
		return pur_order_no;
	}

	public void setPur_order_no(String pur_order_no) {
		this.pur_order_no = pur_order_no;
	}

	public String getTrans_borne_by() {
		return trans_borne_by;
	}

	public void setTrans_borne_by(String trans_borne_by) {
		this.trans_borne_by = trans_borne_by;
	}

	public String getMode_of_trans() {
		return mode_of_trans;
	}

	public void setMode_of_trans(String mode_of_trans) {
		this.mode_of_trans = mode_of_trans;
	}

	public String getTransporter_name() {
		return transporter_name;
	}

	public void setTransporter_name(String transporter_name) {
		this.transporter_name = transporter_name;
	}

	public String getTransport_rate() {
		return transport_rate;
	}

	public void setTransport_rate(String transport_rate) {
		this.transport_rate = transport_rate;
	}

	public String getCharge_code() {
		return charge_code;
	}

	public void setCharge_code(String charge_code) {
		this.charge_code = charge_code;
	}

	public double getRate_value() {
		return rate_value;
	}

	public void setRate_value(double rate_value) {
		this.rate_value = rate_value;
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
