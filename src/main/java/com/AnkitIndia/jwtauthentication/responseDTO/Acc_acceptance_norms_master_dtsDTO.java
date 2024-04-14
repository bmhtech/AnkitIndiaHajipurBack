package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

public class Acc_acceptance_norms_master_dtsDTO {
	
	private String anmd_code;
	
	private String anm_parameter;
	
	private String calculation_basis;
	
	private String basis_amt_uom;
	
	private String anm_min;
	
	private String anm_max;

	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;
	
	public String getAnmd_code() {
		return anmd_code;
	}

	public void setAnmd_code(String anmd_code) {
		this.anmd_code = anmd_code;
	}

	public String getAnm_parameter() {
		return anm_parameter;
	}

	public void setAnm_parameter(String anm_parameter) {
		this.anm_parameter = anm_parameter;
	}

	public String getCalculation_basis() {
		return calculation_basis;
	}

	public void setCalculation_basis(String calculation_basis) {
		this.calculation_basis = calculation_basis;
	}

	public String getBasis_amt_uom() {
		return basis_amt_uom;
	}

	public void setBasis_amt_uom(String basis_amt_uom) {
		this.basis_amt_uom = basis_amt_uom;
	}

	public String getAnm_min() {
		return anm_min;
	}

	public void setAnm_min(String anm_min) {
		this.anm_min = anm_min;
	}

	public String getAnm_max() {
		return anm_max;
	}

	public void setAnm_max(String anm_max) {
		this.anm_max = anm_max;
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
