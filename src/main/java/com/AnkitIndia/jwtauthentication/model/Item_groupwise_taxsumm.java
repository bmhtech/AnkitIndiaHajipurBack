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
@Table(name = "Item_groupwise_taxsumm")
public class Item_groupwise_taxsumm extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoice_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tax_code;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double tax_rate;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double tax_amt;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double percentage;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double cgst;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double sgst;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double igst;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double taxable_amt;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String cgstledgerid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sgstledgerid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String igstledgerid;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "si_id")
	private Sales_Invoice sales_Invoice;
}
