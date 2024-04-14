package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sales_reg_dynamic")
public class Sales_reg_dynamic extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="TEXT")
	private String sales_report;
	
	@Column(columnDefinition="varchar(1000)")
	private String reportfields;
	
	@Column(columnDefinition="varchar(100)", unique=true)
	private String reportname;

	@Column(columnDefinition="varchar(1000)")
	private String table_name;
	
	@Column(columnDefinition = "TEXT")
	private String static_report;
	
	@Column(columnDefinition="varchar(100)")
	private String display_col;
	
	@Column(columnDefinition="varchar(100)")
	private String reporttype;
	
	@Column(columnDefinition="varchar(100)")
	private String reportlist;
	
	@Column(columnDefinition="varchar(100)")
	private String backend;
	//

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReporttype() {
		return reporttype;
	}

	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}

	public String getReportlist() {
		return reportlist;
	}

	public void setReportlist(String reportlist) {
		this.reportlist = reportlist;
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

	public String getReportname() {
		return reportname;
	}

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getStatic_report() {
		return static_report;
	}

	public void setStatic_report(String static_report) {
		this.static_report = static_report;
	}

	public String getDisplay_col() {
		return display_col;
	}

	public void setDisplay_col(String display_col) {
		this.display_col = display_col;
	}

	public String getBackend() {
		return backend;
	}

	public void setBackend(String backend) {
		this.backend = backend;
	}
	
	

}
