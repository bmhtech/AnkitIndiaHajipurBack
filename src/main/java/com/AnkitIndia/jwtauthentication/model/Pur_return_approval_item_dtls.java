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
@Table(name="Pur_return_approval_item_dtls")
public class Pur_return_approval_item_dtls extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String purreturnid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String purreturnno;
	
	private Long slno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String itemcode;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String itemname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String packing;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String packingname;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double quantity;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String uom;
	
	@Column(columnDefinition = "Decimal(10,0) default 0")
	private double squantity;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String suom;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double matwt;
    
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double price;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String pricebasedon;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double amount;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String  discounttype;
    
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double discountrate;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double discountamt;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String  taxcode;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double  taxrate;
    
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double taxamt;
    
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
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
    
    @Column(columnDefinition = "varchar(100) default '0'")
	private String warehouse;
    
    @Column(columnDefinition = "varchar(100) default '0'")
	private String warehouse_name;
    
    @Column(columnDefinition = "varchar(100) default '0'")
	private String stack;
    
    @Column(columnDefinition = "varchar(100) default '0'")
	private String stack_name;
	
    private boolean checkbox;

    @ManyToOne(cascade= CascadeType.ALL)
		@JoinColumn(name = "pr_id")
		private Pur_return_approval_note pur_return_approval_note;

}
