package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sales_dynamic_sort")
public class Sales_dynamic_sort {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50)")
	private String dynamic;
	
	@Column(columnDefinition="varchar(100)")
	private String backend;

	@Column(columnDefinition="varchar(100)")
	private String data_column;
	
	@Column(columnDefinition="varchar(100)")
	private String table_subname;
	
	@Column(columnDefinition="varchar(100)")
	private String reporttype;
	
	@Column(columnDefinition="varchar(100)")
	private String reportlist;
	

	public String getTable_subname() {
		return table_subname;
	}

	public void setTable_subname(String table_subname) {
		this.table_subname = table_subname;
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

	public String getData_column() {
		return data_column;
	}

	public void setData_column(String data_column) {
		this.data_column = data_column;
	}
	

}
