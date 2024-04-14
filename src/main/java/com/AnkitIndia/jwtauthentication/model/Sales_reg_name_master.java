package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sales_reg_name_master")
public class Sales_reg_name_master {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50)")
	private String sales_report;
	
	@Column(columnDefinition="varchar(100)")
	private String reportname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSales_report() {
		return sales_report;
	}

	public void setSales_report(String sales_report) {
		this.sales_report = sales_report;
	}

	public String getReportname() {
		return reportname;
	}

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}







}
