package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sales_inv_dynamic_sorting")
public class Sales_inv_dynamic_sorting extends CommonProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50)")
	private String dynamic;
	
	@Column(columnDefinition="varchar(1000)")
	private String backend;

	@Column(columnDefinition="varchar(50)")
	private String sortNumber;
	
	@Column(columnDefinition="varchar(100)")
	private String reportname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDynamic() {
		return dynamic;
	}

	public void setDynamic(String dynamic) {
		this.dynamic = dynamic;
	}

	public String getBackend() {
		return backend;
	}

	public void setBackend(String backend) {
		this.backend = backend;
	}

	public String getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(String sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getReportname() {
		return reportname;
	}

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	
}
