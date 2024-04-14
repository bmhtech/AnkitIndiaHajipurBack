package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="Accounts_ledger_master")

public class Accounts_ledger_master extends CommonProperties 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(20) default 'NA'")
	private String accts_ledger_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String accts_ledger_code;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String accts_ledger_name;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String accts_ledger_group;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String accts_ledger_group_name;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String alt_ledger_name;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double openbal_ledger;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String gross_profit;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String gst_applicable;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String gst_type;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String accts_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String acc_type_name;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String maintainbalbillbybill;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String inv_val_affected;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String cost_center_ap;

}
