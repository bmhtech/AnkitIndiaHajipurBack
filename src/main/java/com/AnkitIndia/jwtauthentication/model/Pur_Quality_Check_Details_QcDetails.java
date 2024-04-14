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
@Table(name="Pur_Quality_Check_Details_QcDetails")
public class Pur_Quality_Check_Details_QcDetails extends CommonProperties {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quality_check_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String packing_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qc_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qc_param;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cal_basis;
	
	private int sl_no; 
	
	@Column(columnDefinition = "Double(12,3) default 0.000")
	private double basis_amt_UoM;
	
	private int min; 
	
	private int max; 
		
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sample;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String observation;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String out_qc_param;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String master_observation;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String qc_remarks;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="p_qc_id")
	private Pur_Quality_Check_Details pur_Quality_Check_Details;
		
}
