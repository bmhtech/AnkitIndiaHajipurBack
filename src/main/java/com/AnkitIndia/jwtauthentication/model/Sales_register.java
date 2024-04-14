package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="sales_register")

public class Sales_register {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String sales_report;

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

	@Override
	public String toString() {
		return "Sales_register [id=" + id + ", sales_report=" + sales_report + "]";
	}

}
