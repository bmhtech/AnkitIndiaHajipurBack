package com.AnkitIndia.jwtauthentication.model;




import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table(name="Sales_register_details")
public class Sales_register_details {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50)")
	private String sales_report;
	
	@Column(columnDefinition="varchar(50)")
	private String reportfields;

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

	public String getReportfields() {
		return reportfields;
	}

	public void setReportfields(String reportfields) {
		this.reportfields = reportfields;
	}
	

	@Override
	public String toString() {
		return "Sales_register_details [id=" + id + ", sales_report=" + sales_report + ", reportfields=" + reportfields
				+ "]";
	}

	public Sales_register_details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sales_register_details(Long id, String sales_report, String reportfields) {
		super();
		this.id = id;
		this.sales_report = sales_report;
		this.reportfields = reportfields;
	}
	
	public Sales_register_details(String sales_report, String reportfields) {
		super();
		
		this.sales_report = sales_report;
		this.reportfields = reportfields;
	}
	
	

	

	
	
	
	
	
}
