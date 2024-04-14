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
@Table(name="Stk_transfer_pur_inv_item_dtls")
public class Stk_transfer_pur_inv_item_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_trans_pur_inv_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_trans_pur_inv_no;
	
	private int slno;	
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String adv_item_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String adv_item_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String adv_packing_item;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String adv_packing_item_name;
	
	@Column(columnDefinition = "Decimal(10,0) default 0")
	private double passed_packing_qty;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String passed_packing_uom;	
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double passed_item_qty;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double passed_mat_weight;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String passed_item_uom;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double unit_rate;	
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String price_based_on;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double amount;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double discount;	
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String discount_basedon;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double discount_amount;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double net_amount;	
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double qc_deduction;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double net_amt_after_qc;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tax_code;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double tax_rate;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double tax_amt;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double gross_amt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String gl_acc;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean checkbox;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stpi_id")
	private Stk_transfer_pur_inv sTransfer_pur_inv;
}
