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
@Table(name="Nongoodsservice_summary_dyn")
public class Nongoodsservice_summary_dyn extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
 	@Column(columnDefinition="varchar(50) default 0")
	private String nongoodsserviceid;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String charge_name;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String add_less;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String rate_cal_method;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String app_rate;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String tax_rate;
 	
 	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="nid")
 	 private Nongoodsservice nongoodsservice;

 	
}
