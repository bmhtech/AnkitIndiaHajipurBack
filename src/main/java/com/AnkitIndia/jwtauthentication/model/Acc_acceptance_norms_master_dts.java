package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="Acc_acceptance_norms_master_dts")
public class Acc_acceptance_norms_master_dts {

	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Size(max = 50)
	private String anmd_code;
	
	@Size(max = 100)
	private String anm_parameter;
	
	@Size(max = 100)
	private String calculation_basis;
	
	@Size(max = 100)
	private String basis_amt_uom;
	
	@Size(max = 50)
	private String anm_min;
	
	@Size(max = 50)
	private String anm_max;
	
	@Size(max = 50)
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Size(max = 50)
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	@Size(max = 50)
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	@Size(max = 50)
	private String deleted_by;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "Acc_Id")
    private Acc_acceptance_norms_master acc_acceptance_norms_master;

	public Acc_acceptance_norms_master_dts() {
		super();
	}

	public Acc_acceptance_norms_master_dts(Long id, @Size(max = 50) String danmd_code,
			@Size(max = 100) String anm_parameter, @Size(max = 100) String calculation_basis,
			@Size(max = 100) String basis_amt_uom, @Size(max = 50) String anm_min, @Size(max = 50) String anm_max,
			@Size(max = 50) String modified_type, LocalDateTime inserted_on, @Size(max = 50) String inserted_by,
			LocalDateTime updated_on, @Size(max = 50) String updated_by, LocalDateTime deleted_on,
			@Size(max = 50) String deleted_by, Acc_acceptance_norms_master acc_acceptance_norms_master) 
	{
		super();
		this.id = id;
		this.anmd_code = danmd_code;
		this.anm_parameter = anm_parameter;
		this.calculation_basis = calculation_basis;
		this.basis_amt_uom = basis_amt_uom;
		this.anm_min = anm_min;
		this.anm_max = anm_max;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.acc_acceptance_norms_master = acc_acceptance_norms_master;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

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

	public Acc_acceptance_norms_master getAcc_acceptance_norms_master() {
		return acc_acceptance_norms_master;
	}

	public void setAcc_acceptance_norms_master(Acc_acceptance_norms_master acc_acceptance_norms_master) {
		this.acc_acceptance_norms_master = acc_acceptance_norms_master;
	}
	
	
	
}
