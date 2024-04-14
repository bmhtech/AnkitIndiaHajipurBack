package com.AnkitIndia.jwtauthentication.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Acc_gen_ledger_master")
@EntityListeners(AuditingEntityListener.class)

public class Acc_gen_ledger_master {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 50)
	private String galedger_Id;
	
	@Size(max = 50)
	private String galedger_code;
	
	@NotBlank
	@NotNull
	@Size(max = 150)
	private String galedger_name;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  galedger_active;
	
	@NotBlank
	@NotNull
	@Size(max = 50)
	private String galedger_alias;
	
	@NotBlank
	@NotNull
	@Size(max = 50)
	private String asubgroup_code;
	
	@NotBlank
	@NotNull
	@Size(max = 150)
	private String subgroupname;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String  groupname;
	
	@NotBlank
	@NotNull
	@Size(max = 50)
	private String businessunit_code;
	
	@Size(max = 200)
	private String businessunit_name;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean inventory_val;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean cost_center_app;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Column(columnDefinition = "Decimal(12,2)")
	private double open_bal_amt; 
	
	@NotBlank
	@NotNull
	@Size(max = 50)
	private String open_bal_type;
	
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Column(columnDefinition = "Decimal(12,2)")
	private double curr_bal_amt;
	
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

	public String getGaledger_Id() {
		return galedger_Id;
	}

	public void setGaledger_Id(String galedger_Id) {
		this.galedger_Id = galedger_Id;
	}

	public String getGaledger_code() {
		return galedger_code;
	}

	public void setGaledger_code(String galedger_code) {
		this.galedger_code = galedger_code;
	}

	public String getGaledger_name() {
		return galedger_name;
	}

	public void setGaledger_name(String galedger_name) {
		this.galedger_name = galedger_name;
	}

	public boolean isGaledger_active() {
		return galedger_active;
	}

	public void setGaledger_active(boolean galedger_active) {
		this.galedger_active = galedger_active;
	}

	public String getGaledger_alias() {
		return galedger_alias;
	}

	public void setGaledger_alias(String galedger_alias) {
		this.galedger_alias = galedger_alias;
	}

	public String getAsubgroup_code() {
		return asubgroup_code;
	}

	public void setAsubgroup_code(String asubgroup_code) {
		this.asubgroup_code = asubgroup_code;
	}

	public String getSubgroupname() {
		return subgroupname;
	}

	public void setSubgroupname(String subgroupname) {
		this.subgroupname = subgroupname;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
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

	public boolean isInventory_val() {
		return inventory_val;
	}

	public void setInventory_val(boolean inventory_val) {
		this.inventory_val = inventory_val;
	}

	public boolean isCost_center_app() {
		return cost_center_app;
	}

	public void setCost_center_app(boolean cost_center_app) {
		this.cost_center_app = cost_center_app;
	}

	public double getOpen_bal_amt() {
		return open_bal_amt;
	}

	public void setOpen_bal_amt(double open_bal_amt) {
		this.open_bal_amt = open_bal_amt;
	}

	public String getOpen_bal_type() {
		return open_bal_type;
	}

	public void setOpen_bal_type(String open_bal_type) {
		this.open_bal_type = open_bal_type;
	}

	public double getCurr_bal_amt() {
		return curr_bal_amt;
	}

	public void setCurr_bal_amt(double curr_bal_amt) {
		this.curr_bal_amt = curr_bal_amt;
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
