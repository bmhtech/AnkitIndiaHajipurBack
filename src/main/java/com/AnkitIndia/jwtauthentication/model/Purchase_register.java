package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="purchase_register")

public class Purchase_register {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String purchase_report;

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

	@Override
	public String toString() {
		return "Purchase_register [id=" + id + ", purchase_report=" + purchase_report + "]";
	}

}
