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

@Entity
@Table(name="Charge_Masterdtls")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Charge_Masterdtls extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String charge_id;
	
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String charge_name; 
	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String charge_ac;
	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String rate_cal;
	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String method;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tax_code;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double tax_rate;
	
	
	@Column(columnDefinition="varchar(10) default ''")
	private String required;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00" )
	private double app_rate;
	

	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "scharge_id")
    private Charge_Master charge_master;

}
