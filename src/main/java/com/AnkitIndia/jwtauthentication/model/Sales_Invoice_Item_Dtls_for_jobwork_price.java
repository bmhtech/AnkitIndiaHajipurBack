package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Sales_Invoice_Item_Dtls_for_jobwork_price")
public class Sales_Invoice_Item_Dtls_for_jobwork_price extends CommonProperties {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoice_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoice_no;
	
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_service;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_service_name;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_service_acc_name;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String sac_code;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double job_price;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double tax_value;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double cgst_tax;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double cgst_amt;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double sgst_tax;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double sgst_amt;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double tot_amount;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String taxcode;
	
	@Column(columnDefinition="varchar(70) default 'NA'")
	private String taxcode_name;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double igst_tax;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double igst_amt;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "si_id")
	private Sales_Invoice sales_Invoice;

}
