package com.AnkitIndia.jwtauthentication.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name="Pur_goods_receipt_other_information")
public class Pur_goods_receipt_other_information extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grn_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'",nullable=false)
	private String grn_no;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double pty_gross_wt;	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pty_gross_uom;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double pty_tare_wt;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pty_tare_uom;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double pty_net_wt;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pty_net_uom;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pty_weigh_bridge_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pty_weigh_slip_no; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pty_weigh_date;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double own_gross_wt;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String own_gross_uom; 
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double own_tare_wt;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String own_tare_uom;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double own_net_wt;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String own_net_uom;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String own_weigh_bridge_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String own_weigh_slip_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String own_weigh_date;
	
	@Column(columnDefinition = "Double(10,2)")
	private double adv_freight_charge;
	
	@Column(columnDefinition = "Double(10,2)")
	private double freight_paid_amt;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String dc_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String dc_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cn_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cn_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String arg_tax_dtl;
	
	@Column(columnDefinition = "Double(10,2)")
	private double arg_tax_amt;	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle_id;	
	
	@Column(columnDefinition = "Double(10,2)")
	private double bill_amt;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String checkpost_name;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String entry_date;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String remarks;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="P_Ord_Rcv_Id")
	Pur_good_receipt pur_good_receipt;
	
}
