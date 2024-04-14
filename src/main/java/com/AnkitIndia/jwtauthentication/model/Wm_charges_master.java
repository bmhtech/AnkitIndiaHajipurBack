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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="Wm_charges_master")
public class Wm_charges_master extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String wm_charge_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String wm_charge_code;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String wm_charge_desc;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bu_unit;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double amount;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String tax_code;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double tax_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wm_charge_acc;

}
