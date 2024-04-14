package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="Nongoodsservice_summary")
public class Nongoodsservice_summary extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
 	@Column(columnDefinition="varchar(50) default 0")
	private String nongoodsserviceid;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String item_total;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String discount;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String tax_total;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String net_amount;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String app_brokerage;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String net_r_value;
 	
 	
 	
 	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "nid")
    private Nongoodsservice nongoodsservice;
}
