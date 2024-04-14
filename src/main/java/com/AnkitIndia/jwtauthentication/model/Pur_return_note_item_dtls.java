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
@Table(name="Pur_return_note_item_dtls")
public class Pur_return_note_item_dtls extends CommonProperties {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long id;
	
	 @Column(columnDefinition="varchar(50) default 0")
	 private String purreturnnoteid;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String purreturnnoteno;
	 
	 private int slno;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String itemcode;
	 
	 @Column(columnDefinition="varchar(100) default 0")
	 private String itemname;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String packing;
	 
	 @Column(columnDefinition="varchar(100) default 0")
	 private String packingname;
	 
	 @Column(columnDefinition = "Double(10,3) default 0.000")
	 private double quantity;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String uom;
	 
	 @Column(columnDefinition = "Double(10,0) default 0")
	 private double squantity;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String suom;
	 
	 @Column(columnDefinition = "Double(10,3) default 0.000")
	 private double matwt;
	 
	 @Column(columnDefinition = "Double(10,2) default 0.00")
	 private double price;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String pricebasedon;
	 
	 @Column(columnDefinition = "Double(10,2) default 0.00")
	 private double amount;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String discounttype;
	 
	 @Column(columnDefinition = "Double(10,2) default 0.00")
	 private double discountrate;
	 
	 @Column(columnDefinition = "Double(10,2) default 0.00")
	 private double discountamt;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String taxcode;
	 
	 @Column(columnDefinition = "Double(10,2) default 0.00")
	 private double taxrate;
	 
	 @Column(columnDefinition = "Double(10,2) default 0.00")
	 private double taxamt;
	 
	 @Column(columnDefinition = "Double(10,2) default 0.00")
	 private double totalamt;
	 
	 @Column(columnDefinition = "Double(10,2) default 0.00")
	 private double net_amount;
    
     @Column(columnDefinition = "Double(10,2) default 0.00")
	 private double qc_deduction;
    
     @Column(columnDefinition = "Double(10,2) default 0.00")
	 private double net_amt_after_qc;
    
     @Column(columnDefinition = "Double(10,2) default 0.00")
	 private double gross_amt;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String accnorms;
	 
	 @Column(columnDefinition="tinyint(1) default 0")
	 private boolean checkbox;

	 private int bags;
	 
	 @ManyToOne(cascade= CascadeType.ALL)
	 @JoinColumn(name = "prn_id")
	 private Pur_return_note pur_return_note;
	
}
