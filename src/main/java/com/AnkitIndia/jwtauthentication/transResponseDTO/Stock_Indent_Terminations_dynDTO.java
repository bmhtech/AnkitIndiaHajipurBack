package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Stock_Indent_Terminations_dynDTO {
	
	private Long id;
	
	private String indent_id;
	
	private String indent_no;
	
	private String charge_name;
	
	private String termination_cal;
	
	private String cal_qty;
	
	private double amount;
	
	private String method;
	
	private double tax_rate;

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

	public String getCharge_name() {
		return charge_name;
	}

	public void setCharge_name(String charge_name) {
		this.charge_name = charge_name;
	}

	public String getTermination_cal() {
		return termination_cal;
	}

	public void setTermination_cal(String termination_cal) {
		this.termination_cal = termination_cal;
	}

	public String getCal_qty() {
		return cal_qty;
	}

	public void setCal_qty(String cal_qty) {
		this.cal_qty = cal_qty;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public double getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(double tax_rate) {
		this.tax_rate = tax_rate;
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
