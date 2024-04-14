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
@Table(name="Wm_unload_advice_item_dtls_for_jobwork")
public class Wm_unload_advice_item_dtls_for_jobwork extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String unadviceid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String unadviceno;
	
	private Long sl_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String job_item;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String job_item_name;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String job_packing;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String job_packing_name;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String job_hsn;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double pack_qty;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String pack_uom;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String price_based_on;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double item_qty;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_uom;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String tolerance;
	
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wmula_id")
    private Wm_unload_advice wm_unload_advice;
}
