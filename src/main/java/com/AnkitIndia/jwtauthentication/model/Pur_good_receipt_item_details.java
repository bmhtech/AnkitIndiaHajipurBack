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
@Table(name="Pur_good_receipt_item_details")
public class Pur_good_receipt_item_details extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String grn_id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'",nullable=false)
	private String grn_no;
	
	private int slno;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String adv_item_code;
	
	@Column(columnDefinition = "varchar(500) default 'NA'")
	private String adv_item_name;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String hsn_code;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String adv_packing;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String adv_packing_name;
	
	@Column(columnDefinition = "Double(10,2)")
	private double adv_pack_qty; 
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String adv_pack_uom; 
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double adv_item_qty;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double adv_mat_wt; 
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String adv_item_uom; 
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double rcv_pack_qty; 
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String rcv_pack_uom; 
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double rcv_item_qty; 
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double rcv_mat_wt; 
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String rcv_item_uom;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double pssd_pack_qty;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String pssd_pack_uom;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double pssd_item_qty;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double pssd_mat_wt;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String pssd_item_uom;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String con_factor;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double unit_rate;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String price_based_on;	
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double amount;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double discount;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String discount_based_on;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double discount_amt;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double net_amt;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String qc_deduction;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double net_amt_after_qc;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String tax_code;
	
	
	
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double tax_rate;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double cgstamt;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double sgstamt;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double igstamt;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double tax_amt;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double gross_amt;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String qc_norms;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String warehouse_name;	
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String rack;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String stack_uom;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double stack_qty;
	
	private int bags;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String classified_item_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_item_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_size;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_weight;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_type;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="P_Ord_Rcv_Id")
	Pur_good_receipt pur_good_receipt;
	
}
