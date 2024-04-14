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
@Table(name="Bom_resource_cost")
public class Bom_resource_cost extends CommonProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String production_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String production_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String charge_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String rate_cal_method;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double amount;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double tax_rate;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double tax_amt;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double gross_amt;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "bmid")
    private Bom_master bom_master;
}
