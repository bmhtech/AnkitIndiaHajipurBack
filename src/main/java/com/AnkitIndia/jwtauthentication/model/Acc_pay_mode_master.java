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
@Table(name="Acc_pay_mode_master")
@EntityListeners(AuditingEntityListener.class)

public class Acc_pay_mode_master {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 50)
	private String paymode_id;
	
	@Size(max = 50)
	private String paymode_code;
	
	@Size(max = 50)
	private String company_id;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String paymode_name;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  paymode_active;
	
	@NotBlank
	@NotNull
	@Size(max = 50)
	private String buint_code;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String bunit_name;
	
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
	

	public String getPaymode_id() {
		return paymode_id;
	}

	public void setPaymode_id(String paymode_id) {
		this.paymode_id = paymode_id;
	}

	public String getPaymode_code() {
		return paymode_code;
	}

	public void setPaymode_code(String paymode_code) {
		this.paymode_code = paymode_code;
	}

	public String getPaymode_name() {
		return paymode_name;
	}

	public void setPaymode_name(String paymode_name) {
		this.paymode_name = paymode_name;
	}

	public boolean isPaymode_active() {
		return paymode_active;
	}

	public void setPaymode_active(boolean paymode_active) {
		this.paymode_active = paymode_active;
	}

	public String getBuint_code() {
		return buint_code;
	}

	public void setBuint_code(String buint_code) {
		this.buint_code = buint_code;
	}

	public String getBunit_name() {
		return bunit_name;
	}

	public void setBunit_name(String bunit_name) {
		this.bunit_name = bunit_name;
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

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	
}
