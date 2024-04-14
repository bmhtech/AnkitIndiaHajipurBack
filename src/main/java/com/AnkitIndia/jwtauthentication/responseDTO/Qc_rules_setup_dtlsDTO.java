package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;


public class Qc_rules_setup_dtlsDTO {

	private String company_id;
	
	private String qc_id;
	
	private String qc_code;
	
	private String qc_param;
	
	private String cal_basis;
	
	private boolean qty_chkbox;
	
	private double basis_amt_UoM; 
	
	private double min; 
	
	private double max; 
	
	private String fin_year;
	
	private String  modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getQc_id() {
		return qc_id;
	}

	public void setQc_id(String qc_id) {
		this.qc_id = qc_id;
	}

	public String getQc_code() {
		return qc_code;
	}

	public void setQc_code(String qc_code) {
		this.qc_code = qc_code;
	}

	public String getQc_param() {
		return qc_param;
	}

	public void setQc_param(String qc_param) {
		this.qc_param = qc_param;
	}

	public String getCal_basis() {
		return cal_basis;
	}

	public void setCal_basis(String cal_basis) {
		this.cal_basis = cal_basis;
	}

	public boolean isQty_chkbox() {
		return qty_chkbox;
	}

	public void setQty_chkbox(boolean qty_chkbox) {
		this.qty_chkbox = qty_chkbox;
	}

	public double getBasis_amt_UoM() {
		return basis_amt_UoM;
	}

	public void setBasis_amt_UoM(double basis_amt_UoM) {
		this.basis_amt_UoM = basis_amt_UoM;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
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
