package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Stock_Indent_TerminationsDTO {
	
	private Long id;
	
	private String indent_id;
	
	private String indent_no;
	
	private String term_pur_ord;
	
	private String order_by;
	
	private String reason;
	
	private String remarks;
	
	private double tot_term_chg;
	
	private double term_add;
	
	private double term_deduct;
	
	private double net_term_chg;
	
	private String charges_descpt;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndent_id() {
		return indent_id;
	}

	public void setIndent_id(String indent_id) {
		this.indent_id = indent_id;
	}

	public String getIndent_no() {
		return indent_no;
	}

	public void setIndent_no(String indent_no) {
		this.indent_no = indent_no;
	}

	public String getTerm_pur_ord() {
		return term_pur_ord;
	}

	public void setTerm_pur_ord(String term_pur_ord) {
		this.term_pur_ord = term_pur_ord;
	}

	public String getOrder_by() {
		return order_by;
	}

	public void setOrder_by(String order_by) {
		this.order_by = order_by;
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

	public String getCharges_descpt() {
		return charges_descpt;
	}

	public void setCharges_descpt(String charges_descpt) {
		this.charges_descpt = charges_descpt;
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
