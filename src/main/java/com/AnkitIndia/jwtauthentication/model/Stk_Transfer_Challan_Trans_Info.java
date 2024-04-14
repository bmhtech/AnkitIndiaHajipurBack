package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="Stk_Transfer_Challan_Trans_Info")
public class Stk_Transfer_Challan_Trans_Info extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_challan_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_challan_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String trans_borne_by;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String mode_of_trans;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_id;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double freight_amt;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double  adv_paid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String charge_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String trans_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String payment_term;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stc_id")
	private Stk_Transfer_Challan stk_Transfer_Challan;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Challan_Trans_Info",cascade = CascadeType.ALL)
	private Stk_transfer_grn_trans_info stk_transfer_grn_trans_info;
}
