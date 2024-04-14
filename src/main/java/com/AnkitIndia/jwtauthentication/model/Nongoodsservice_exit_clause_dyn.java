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
@Table(name="Nongoodsservice_exit_clause_dyn")
public class Nongoodsservice_exit_clause_dyn extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
	@Column(columnDefinition="varchar(50) default 0")
	private String nongoodsserviceid;
	
 	@Column(columnDefinition="varchar(50) default 0")
	private String charge_name;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String termination_cal;
	
 	@Column(columnDefinition="varchar(50) default 0")
	private String method
	;
 	@Column(columnDefinition="varchar(50) default 0")
	private String cal_qty
	;
 	@Column(columnDefinition="varchar(50) default 0")
	private String qty;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String amount;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String rate;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String gl_account;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String tax_rate
	;
 	@Column(columnDefinition="varchar(50) default 0")
	private String tax_amount;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String total_amount;
 	
 	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="nid")
 	private Nongoodsservice nongoodsservice;
}
