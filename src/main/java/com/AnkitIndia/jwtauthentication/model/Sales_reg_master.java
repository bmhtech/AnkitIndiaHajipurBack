package com.AnkitIndia.jwtauthentication.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="sales_reg_master")

public class Sales_reg_master extends CommonProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(1000)")
	private String sales_report;
	
	@Column(columnDefinition="varchar(1000)")
	private String reportfields;
	
	@Column(columnDefinition="varchar(1000)")
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

	
	
}
