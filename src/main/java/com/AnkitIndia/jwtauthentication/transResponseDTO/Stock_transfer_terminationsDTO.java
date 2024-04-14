package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

public class Stock_transfer_terminationsDTO {
	
	private Long id;
	
	private String order_id;
	
	private String order_no;
	
	private boolean term_stk_ord;
	
	private String order_by;
	
	private String charges_descpt;
	
	private String reason;
	
	private String remarks;  
	
	private double tot_term_chg;
	
	private double term_add;
	
	private double term_deduct;
	
	private double net_term_chg;
	
	private String company_id;
	
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

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public boolean isTerm_stk_ord() {
		return term_stk_ord;
	}

	public void setTerm_stk_ord(boolean term_stk_ord) {
		this.term_stk_ord = term_stk_ord;
	}

	public String getOrder_by() {
		return order_by;
	}

	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}

	public String getCharges_descpt() {
		return charges_descpt;
	}

	public void setCharges_descpt(String charges_descpt) {
		this.charges_descpt = charges_descpt;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getTot_term_chg() {
		return tot_term_chg;
	}

	public void setTot_term_chg(double tot_term_chg) {
		this.tot_term_chg = tot_term_chg;
	}

	public double getTerm_add() {
		return term_add;
	}

	public void setTerm_add(double term_add) {
		this.term_add = term_add;
	}

	public double getTerm_deduct() {
		return term_deduct;
	}

	public void setTerm_deduct(double term_deduct) {
		this.term_deduct = term_deduct;
	}

	public double getNet_term_chg() {
		return net_term_chg;
	}

	public void setNet_term_chg(double net_term_chg) {
		this.net_term_chg = net_term_chg;
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
