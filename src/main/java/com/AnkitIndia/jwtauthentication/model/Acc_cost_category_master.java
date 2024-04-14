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
@Table(name="Acc_cost_category_master")
@EntityListeners(AuditingEntityListener.class)

public class Acc_cost_category_master {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 50)
	private String costcat_Id;
	
	@Size(max = 50)
	private String costcat_code;
	
	@NotBlank
	@NotNull
	@Size(max = 150)
	private String costcat_name;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  costcat_active;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean allo_rev_items;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean allo_non_rev_items;
	
	@NotBlank
	@NotNull
	@Size(max = 50)
	private String businessunit_code;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String businessunit_name;
	
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

	public String getCostcat_Id() {
		return costcat_Id;
	}

	public void setCostcat_Id(String costcat_Id) {
		this.costcat_Id = costcat_Id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isCostcat_active() {
		return costcat_active;
	}

	public void setCostcat_active(boolean costcat_active) {
		this.costcat_active = costcat_active;
	}

	public boolean isAllo_rev_items() {
		return allo_rev_items;
	}

	public void setAllo_rev_items(boolean allo_rev_items) {
		this.allo_rev_items = allo_rev_items;
	}

	public boolean isAllo_non_rev_items() {
		return allo_non_rev_items;
	}

	public void setAllo_non_rev_items(boolean allo_non_rev_items) {
		this.allo_non_rev_items = allo_non_rev_items;
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
