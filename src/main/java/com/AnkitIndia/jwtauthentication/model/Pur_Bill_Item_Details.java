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
@Table(name="Pur_Bill_Item_Details")
public class Pur_Bill_Item_Details extends CommonProperties {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long slno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_bill_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_bill_no;
	     
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String adv_item_code;
	
	@Column(columnDefinition = "varchar(500) default 'NA'")
	private String adv_item_name;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String item_group;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String adv_packing_item;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String adv_packing_item_name;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String hsn_code;
	
    //private long passed_packing_qty;
    
    @Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double passed_packing_qty;
    
    @Column(columnDefinition = "varchar(50) default 'NA'")
	private String passed_packing_uom;
    
    @Column(columnDefinition = "Double(10,3) default 0.000")
    private double passed_item_qty;
    
    @Column(columnDefinition = "Double(10,3) default 0.000")
	private double passed_mat_weight;
    
    @Column(columnDefinition = "varchar(50) default 'NA'")
	private String passed_item_uom;	
    
    @Column(columnDefinition = "Double(10,2)")
	private double  unit_rate;
    
    @Column(columnDefinition = "varchar(50) default 'NA'")
	private String price_based_on;
    
    @Column(columnDefinition = "Double(10,2)")
	private double amount;
    
    @Column(columnDefinition = "Double(10,2)")
	private double discount;
    
    @Column(columnDefinition = "varchar(50) default 'NA'")
	private String discount_basedon;	
    
    @Column(columnDefinition = "Double(10,2)")
	private double discount_amount;
    
    @Column(columnDefinition = "Double(10,2)")
	private double net_amount;
    
    @Column(columnDefinition = "Double(10,2)")
	private double qc_deduction;
    
    @Column(columnDefinition = "Double(10,2)")
	private double net_amt_after_qc;
    
    @Column(columnDefinition = "varchar(50) default 'NA'")
	private String tax_code;
    
    @Column(columnDefinition = "varchar(50) default 'NA'")
	private String tax_name;
    
    @Column(columnDefinition = "Double(10,2) default 0.00" )
	private double tax_rate;
  
    @Column(columnDefinition = "Double(10,2) default 0.00" )
	private double cgstamt;
    
    @Column(columnDefinition = "Double(10,2) default 0.00" )
	private double sgstamt;
    
    @Column(columnDefinition = "Double(10,2) default 0.00" )
	private double igstamt;
    
    @Column(columnDefinition = "Double(10,2)")
	private double tax_amt;
    
    @Column(columnDefinition = "Double(10,2)")
	private double gross_amt;
    
    @Column(columnDefinition = "varchar(50) default 'NA'")
	private String gl_acc;
    
    @Column(columnDefinition = "varchar(100) default 'NA'")
	private String warehouse;
    
    @Column(columnDefinition = "varchar(100) default 'NA'")
	private String warehouse_name;
    
    @Column(columnDefinition = "varchar(100) default 'NA'")
	private String stack;
    
    @Column(columnDefinition = "varchar(100) default 'NA'")
	private String stack_name;
   
    @Column(columnDefinition="tinyint(1) default 0")
	private boolean checkbox;
    
    @Column(columnDefinition = "varchar(100) default 'NA'")
	private String classified_item_name;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "P_Id")
	private Pur_Bill pur_Bill;

}
