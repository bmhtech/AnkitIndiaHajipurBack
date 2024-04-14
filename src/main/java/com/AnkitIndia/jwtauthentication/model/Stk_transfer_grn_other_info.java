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
@Table(name="Stk_transfer_grn_other_info")
public class Stk_transfer_grn_other_info extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_no;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double pty_gross_wt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String pty_gross_uom;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double pty_tare_wt; 
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String pty_tare_uom;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double pty_net_wt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String pty_net_uom;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String pty_weigh_bridge_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String pty_weigh_slip_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String pty_weigh_date;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double own_gross_wt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String own_gross_uom; 
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double own_tare_wt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String own_tare_uom;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double own_net_wt;  
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String own_net_uom;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String own_weigh_bridge_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String own_weigh_slip_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String own_weigh_date;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double adv_freight_charge; 
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double freight_paid_amt;	
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String dc_no;	
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String dc_date;	
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String cn_no;	
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String cn_date;	
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String arg_tax_dtl;	
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double arg_tax_amt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_id;	
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double bill_amt;	
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String checkpost_name;  
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String entry_date;
	
	@Column(columnDefinition="varchar(500) default '0'")
	private String remarks;
	
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stkgrn_id")
	private Stk_transfer_grn sTransfer_grn;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stc_id")
	private Stk_Transfer_Challan stk_Transfer_Challan;
}
