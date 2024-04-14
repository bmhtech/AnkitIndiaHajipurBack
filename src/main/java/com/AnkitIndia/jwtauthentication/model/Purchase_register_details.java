package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="purchase_register_details")

public class Purchase_register_details {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50)")
	private String purchase_report;
	
	@Column(columnDefinition="varchar(50)")
	private String reportfields;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPurchase_report() {
		return purchase_report;
	}

	public void setPurchase_report(String purchase_report) {
		this.purchase_report = purchase_report;
	}

	public String getReportfields() {
		return reportfields;
	}

	public void setReportfields(String reportfields) {
		this.reportfields = reportfields;
	}

	@Override
	public String toString() {
		return "Purchase_register_details [id=" + id + ", purchase_report=" + purchase_report + ", reportfields="
				+ reportfields + "]";
	}

}
