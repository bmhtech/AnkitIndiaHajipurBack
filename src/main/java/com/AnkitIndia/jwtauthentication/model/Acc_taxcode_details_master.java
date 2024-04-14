package com.AnkitIndia.jwtauthentication.model;


import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Acc_taxcode_details_master")

public class Acc_taxcode_details_master {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 50)
	private String tax_code;
	
	//@NotBlank
	//@NotNull
	@Size(max = 100)
	private String tax_name;
	
	//@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  taxcode_active;
	
	//@NotBlank
	//@NotNull
	@Size(max = 150)
	private String tax_description;
	
	//@NotBlank
	//@NotNull
	@Size(max = 50)
	private String businessunit_code;
	
	//@NotBlank
	//@NotNull
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
	
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="acc_taxcode_details_master",cascade = CascadeType.ALL)
	private Set<Acc_taxcode_details_master_dts> acc_taxcode_details_master_dts;
	
	public Acc_taxcode_details_master() {
		super();
	}
	
	

	public Acc_taxcode_details_master(Long id, @Size(max = 50) String tax_code, @Size(max = 100) String tax_name,
			boolean taxcode_active, @Size(max = 150) String tax_description, @Size(max = 50) String businessunit_code,
			@Size(max = 150) String businessunit_name, @Size(max = 50) String modified_type, LocalDateTime inserted_on,
			@Size(max = 50) String inserted_by, LocalDateTime updated_on, @Size(max = 50) String updated_by,
			LocalDateTime deleted_on, @Size(max = 50) String deleted_by,
			Set<Acc_taxcode_details_master_dts> acc_taxcode_details_master_dts) {
		super();
		this.id = id;
		this.tax_code = tax_code;
		this.tax_name = tax_name;
		this.taxcode_active = taxcode_active;
		this.tax_description = tax_description;
		this.businessunit_code = businessunit_code;
		this.businessunit_name = businessunit_name;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.acc_taxcode_details_master_dts = acc_taxcode_details_master_dts;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTax_code() {
		return tax_code;
	}

	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}

	public String getTax_name() {
		return tax_name;
	}

	public void setTax_name(String tax_name) {
		this.tax_name = tax_name;
	}

	public boolean isTaxcode_active() {
		return taxcode_active;
	}

	public void setTaxcode_active(boolean taxcode_active) {
		this.taxcode_active = taxcode_active;
	}

	public String getTax_description() {
		return tax_description;
	}

	public void setTax_description(String tax_description) {
		this.tax_description = tax_description;
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

	public Set<Acc_taxcode_details_master_dts> getAcc_taxcode_details_master_dts() {
		return acc_taxcode_details_master_dts;
	}


	public void setAcc_taxcode_details_master_dts(Set<Acc_taxcode_details_master_dts> acc_taxcode_details_master_dts) {
		this.acc_taxcode_details_master_dts = acc_taxcode_details_master_dts;
	}


	@Override
	public String toString() {
		return "Acc_taxcode_details_master [id=" + id + ", tax_code=" + tax_code + ", tax_name=" + tax_name
				+ ", taxcode_active=" + taxcode_active + ", tax_description=" + tax_description + ", businessunit_code="
				+ businessunit_code + ", businessunit_name=" + businessunit_name + ", modified_type=" + modified_type
				+ ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by + ", updated_on=" + updated_on
				+ ", updated_by=" + updated_by + ", deleted_on=" + deleted_on + ", deleted_by=" + deleted_by
				+ ", acc_taxcode_details_master_dts=" + acc_taxcode_details_master_dts + "]";
	}

	
	
	
}
