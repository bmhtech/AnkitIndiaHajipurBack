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
@Table(name = "Purchase_item_groupwise_taxsumm")

public class Purchase_item_groupwise_taxsumm extends CommonProperties{
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_bill_id;
	
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
	    @JoinColumn(name = "P_Id")
		private Pur_Bill pur_Bill;

}
