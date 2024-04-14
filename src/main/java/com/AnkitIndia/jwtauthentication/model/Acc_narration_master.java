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
@Table(name="Acc_narration_master")
@EntityListeners(AuditingEntityListener.class)

public class Acc_narration_master {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 50)
	private String narration_Id;
	
	@Size(max = 50)
	private String narration_code;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String narration_name;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  narration_active;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String description_head;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String narration_line1;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String narration_line2;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String  narration_line3;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String narration_line4;
	
	@Size(max = 100)
	private String narration_line5;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String businessunit_code;
	
	@NotBlank
	@NotNull
	@Size(max = 150)
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

	public String getNarration_Id() {
		return narration_Id;
	}

	public void setNarration_Id(String narration_Id) {
		this.narration_Id = narration_Id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNarration_code() {
		return narration_code;
	}

	public void setNarration_code(String narration_code) {
		this.narration_code = narration_code;
	}

	public String getNarration_name() {
		return narration_name;
	}

	public void setNarration_name(String narration_name) {
		this.narration_name = narration_name;
	}

	public boolean isNarration_active() {
		return narration_active;
	}

	public void setNarration_active(boolean narration_active) {
		this.narration_active = narration_active;
	}

	public String getDescription_head() {
		return description_head;
	}

	public void setDescription_head(String description_head) {
		this.description_head = description_head;
	}

	public String getNarration_line1() {
		return narration_line1;
	}

	public void setNarration_line1(String narration_line1) {
		this.narration_line1 = narration_line1;
	}

	public String getNarration_line2() {
		return narration_line2;
	}

	public void setNarration_line2(String narration_line2) {
		this.narration_line2 = narration_line2;
	}

	public String getNarration_line3() {
		return narration_line3;
	}

	public void setNarration_line3(String narration_line3) {
		this.narration_line3 = narration_line3;
	}

	public String getNarration_line4() {
		return narration_line4;
	}

	public void setNarration_line4(String narration_line4) {
		this.narration_line4 = narration_line4;
	}

	public String getNarration_line5() {
		return narration_line5;
	}

	public void setNarration_line5(String narration_line5) {
		this.narration_line5 = narration_line5;
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
