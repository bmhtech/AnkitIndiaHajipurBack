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
@Table(name="Pur_bill_store_chgs")
public class Pur_bill_store_chgs extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long slno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_bill_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_bill_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String charges_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String charges_acc;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String charges_acc_code;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double store_cgst;
    
    @Column(columnDefinition = "Double(10,2) default 0.00" )
	private double store_sgst;
    
    @Column(columnDefinition = "Double(10,2) default 0.00" )
	private double store_igst;
    
	@Column(columnDefinition = "Double(10,2)  default 0.00")
	private double store_amount;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double store_taxrate;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "P_Id")
	private Pur_Bill pur_Bill;
	
}
