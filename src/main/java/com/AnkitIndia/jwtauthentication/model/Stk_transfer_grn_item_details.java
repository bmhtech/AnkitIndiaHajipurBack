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
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Stk_transfer_grn_item_details")
public class Stk_transfer_grn_item_details extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_no;
	
	private int slno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String adv_item_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String adv_item_name; 
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String adv_packing_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String adv_packing; 
	
	@Column(columnDefinition = "Double(10,0) default 0")
	private double adv_pack_qty; 
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String adv_pack_uom; 
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double adv_item_qty;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double adv_mat_wt; 
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String adv_item_uom; 
	
	@Column(columnDefinition = "Double(10,0) default 0")
	private double rcv_pack_qty; 
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String rcv_pack_uom; 
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double rcv_item_qty; 
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double rcv_mat_wt; 
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String rcv_item_uom; 
	
	@Column(columnDefinition = "Double(10,0) default 0")
	private double pssd_pack_qty; 
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String pssd_pack_uom; 
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double pssd_item_qty; 
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double pssd_mat_wt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String pssd_item_uom; 
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double unit_rate;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String price_based_on;	
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double amount; 	
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double discount;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String discount_based_on;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double discount_amt;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double net_amt;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double qc_deduction;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double net_amt_after_qc;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tax_code;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double tax_rate;	
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double tax_amt;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double gross_amt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String qc_norms;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String warehouse;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String warehouse_name;	
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String rack;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String rack_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stack_uom;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double stack_qty;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean checkbox;										
	
	private int bags;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stkgrn_id")
	private Stk_transfer_grn sTransfer_grn;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stc_id")
	private Stk_Transfer_Challan_Item_Dtls stk_Transfer_Challan_Item_Dtls;
}
