package com.AnkitIndia.jwtauthentication.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Acc_cost_centre_master")
@EntityListeners(AuditingEntityListener.class)

public class Acc_cost_centre_master {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 50)
	private String costcentre_Id;
	
	@Size(max = 50)
	private String costcentre_code;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String costcentre_name;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  costcentre_active;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String costcat_code;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String costcat_name;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String under_costcentre;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  job_cost;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean pro_bank_dts;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String businessunit_code;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String businessunit_name;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String transaction_type;
	
	@NotBlank
	@NotNull
	@Size(max = 50)
	private String acc_number;
	
	@NotBlank
	@NotNull
	@Size(max = 50)
	private String ifsc_code;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String bank_name;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCostcentre_Id() {
		return costcentre_Id;
	}

	public void setCostcentre_Id(String costcentre_Id) {
		this.costcentre_Id = costcentre_Id;
	}

	public String getCostcentre_code() {
		return costcentre_code;
	}

	public void setCostcentre_code(String costcentre_code) {
		this.costcentre_code = costcentre_code;
	}

	public String getCostcentre_name() {
		return costcentre_name;
	}

	public void setCostcentre_name(String costcentre_name) {
		this.costcentre_name = costcentre_name;
	}

	public boolean isCostcentre_active() {
		return costcentre_active;
	}

	public void setCostcentre_active(boolean costcentre_active) {
		this.costcentre_active = costcentre_active;
	}

	public String getCostcat_code() {
		return costcat_code;
	}

	public void setCostcat_code(String costcat_code) {
		this.costcat_code = costcat_code;
	}

	public String getCostcat_name() {
		return costcat_name;
	}

	public void setCostcat_name(String costcat_name) {
		this.costcat_name = costcat_name;
	}

	public String getUnder_costcentre() {
		return under_costcentre;
	}

	public void setUnder_costcentre(String under_costcentre) {
		this.under_costcentre = under_costcentre;
	}

	public boolean isJob_cost() {
		return job_cost;
	}

	public void setJob_cost(boolean job_cost) {
		this.job_cost = job_cost;
	}

	public boolean isPro_bank_dts() {
		return pro_bank_dts;
	}

	public void setPro_bank_dts(boolean pro_bank_dts) {
		this.pro_bank_dts = pro_bank_dts;
	}

	public String getBusinessunit_code() {
		return businessunit_code;
	}

	public void setBusinessunit_code(String businessunit_code) {
		this.businessunit_code = businessunit_code;
	}

	public String getBusinessunit_name() {
		return businessunit_name;
	}

	public void setBusinessunit_name(String businessunit_name) {
		this.businessunit_name = businessunit_name;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public String getAcc_number() {
		return acc_number;
	}

	public void setAcc_number(String acc_number) {
		this.acc_number = acc_number;
	}

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
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
