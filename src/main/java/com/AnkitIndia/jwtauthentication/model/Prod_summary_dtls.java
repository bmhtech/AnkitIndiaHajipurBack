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
@Table(name="Prod_summary_dtls")
public class Prod_summary_dtls extends CommonProperties
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long sl_no;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String prod_sum_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing;
	
	@Column(columnDefinition="varchar(200) default 'NA'")  
	private String packing_name;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String uom_basedon;
	
	@Column(columnDefinition="Decimal(10,2) default 0.00")
	private double capacity;
	
	@Column(columnDefinition="Decimal(10,2) default 0.00")
	private double empty_big_wt;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String empbagwt_based_on;
	
	@Column(columnDefinition="Decimal(10,2) default 0.00")
	private double packing_qty;
	
	@Column(columnDefinition="Decimal(10,3) default 0.00")
	private double production_qty;
	
	@Column(columnDefinition="Decimal(10,2) default '0.00'")
	private double rate;
	
	@Column(columnDefinition="Decimal(10,2) default '0.00'")
	private double amount;
	
	//@Column(columnDefinition="varchar(500) default 'NA'")
	//private String remarks; 
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String packing_uom; 
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String production_uom; 
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ps_id")
    private Prodsummary prodsummary;
}
