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
@Table(name="Acc_sub_group_master")
@EntityListeners(AuditingEntityListener.class)

public class Acc_sub_group_master {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 50)
	private String subgroup_Id;
	
	public String getSubgroup_Id() {
		return subgroup_Id;
	}

	public void setSubgroup_Id(String subgroup_Id) {
		this.subgroup_Id = subgroup_Id;
	}

	@Size(max = 50)
	private String subgroup_code;
	
	@NotBlank
	@NotNull
	@Size(max = 150)
	private String subgroup_name;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  subgroup_active;
	
	@NotBlank
	@NotNull
	@Size(max = 50)
	private String group_code;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String groupname;
	
	@NotBlank
	@NotNull
	@Size(max = 50)
	private String businessunit_code;
	
	@NotBlank
	@NotNull
	@Size(max = 200)
	private String businessunit_name;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Column(columnDefinition = "Decimal(12,2)")
	private double open_bal_amt;
	
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

	public String getSubgroup_code() {
		return subgroup_code;
	}

	public void setSubgroup_code(String subgroup_code) {
		this.subgroup_code = subgroup_code;
	}

	public String getSubgroup_name() {
		return subgroup_name;
	}

	public void setSubgroup_name(String subgroup_name) {
		this.subgroup_name = subgroup_name;
	}

	public boolean isSubgroup_active() {
		return subgroup_active;
	}

	public void setSubgroup_active(boolean subgroup_active) {
		this.subgroup_active = subgroup_active;
	}

	public String getGroup_code() {
		return group_code;
	}

	public void setGroup_code(String group_code) {
		this.group_code = group_code;
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

	public double getOpen_bal_amt() {
		return open_bal_amt;
	}

	public void setOpen_bal_amt(double open_bal_amt) {
		this.open_bal_amt = open_bal_amt;
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
