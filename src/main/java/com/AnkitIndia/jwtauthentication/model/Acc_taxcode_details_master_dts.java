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
@Table(name="Acc_taxcode_details_master_dts")
public class Acc_taxcode_details_master_dts {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Size(max = 50)
	private String tax_code;
	
	private Long sl_no;
	
	@Size(max = 50)
	private String taxtype_code;
	
	@Size(max = 150)
	private String taxtype_name;
	
	@Size(max = 20)
	private String tax_rate;
	
	
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
    @JoinColumn(name = "Acctax_Id")
    private Acc_taxcode_details_master acc_taxcode_details_master;


	public Acc_taxcode_details_master_dts() {
		super();
	}


	public Acc_taxcode_details_master_dts(Long id, @Size(max = 50) String tax_code, Long sl_no,
			@Size(max = 50) String taxtype_code, @Size(max = 150) String taxtype_name, @Size(max = 20) String tax_rate,
			@Size(max = 50) String modified_type, LocalDateTime inserted_on, @Size(max = 50) String inserted_by,
			LocalDateTime updated_on, @Size(max = 50) String updated_by, LocalDateTime deleted_on,
			@Size(max = 50) String deleted_by, Acc_taxcode_details_master acc_taxcode_details_master) {
		super();
		this.id = id;
		this.tax_code = tax_code;
		this.sl_no = sl_no;
		this.taxtype_code = taxtype_code;
		this.taxtype_name = taxtype_name;
		this.tax_rate = tax_rate;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.acc_taxcode_details_master = acc_taxcode_details_master;
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


	public Long getSl_no() {
		return sl_no;
	}


	public void setSl_no(Long sl_no) {
		this.sl_no = sl_no;
	}


	public String getTaxtype_code() {
		return taxtype_code;
	}


	public void setTaxtype_code(String taxtype_code) {
		this.taxtype_code = taxtype_code;
	}


	public String getTaxtype_name() {
		return taxtype_name;
	}


	public void setTaxtype_name(String taxtype_name) {
		this.taxtype_name = taxtype_name;
	}


	public String getTax_rate() {
		return tax_rate;
	}


	public void setTax_rate(String tax_rate) {
		this.tax_rate = tax_rate;
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


	public Acc_taxcode_details_master getAcc_taxcode_details_master() {
		return acc_taxcode_details_master;
	}


	public void setAcc_taxcode_details_master(Acc_taxcode_details_master acc_taxcode_details_master) {
		this.acc_taxcode_details_master = acc_taxcode_details_master;
	}
	
	
	
	
}
