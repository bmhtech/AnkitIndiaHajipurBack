package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;

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
@Table(name="Wheat_fumigation_details")
public class Wheat_fumigation_details extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String fumigation_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String fumigation_no;
	
	private int slno; 
	
	private Date wf_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wf_time;
	
	//@Column(columnDefinition="varchar(100) default '0'")
	//private String abd_trader;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String warehouse;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String warehouse_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String stack;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String variety;
	
	@Column(columnDefinition = "Decimal(20,0) default 0")
	private double bags;
	
	@Column(columnDefinition = "Decimal(20,3) default 0.000")
	private double qty_mt;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String fumigation_by;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String reactant;
	
	@Column(columnDefinition = "Decimal(20,2) default 0.0")
	private double volume;
	
	@Column(columnDefinition = "Decimal(20,2) default 0.0")
	private double dose;
	
	@Column(columnDefinition = "Decimal(20,2) default 0.0")
	private double total_dose;
	
	private Date degassing_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String degassing_time;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String manpower;
	
	private Date expected_stack_opening_date;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String pcmw_sign;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String pcmw_sign_name;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String supervisor_sign;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String supervisor_sign_name;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String lab_sign;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String lab_sign_name;
	
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String stack_open_date;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String stack_use;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String allocate_status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wheat_fumi_qc;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "fu_id")
	private Wheat_fumigation wheat_fumigation;
}
