package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Wm_loading_advice_Item_Dtls_for_jobwork")
public class Wm_loading_advice_Item_Dtls_for_jobwork extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String advice_id;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String advice_no;
	
	private int slno;
	
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
	
	@Column(columnDefinition = "Double(12,3) default 0.000")
	private double job_tolerance_qty;
	
	@Column(columnDefinition = "varchar(20) default 'NA'")
  	private String order_id;
           
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wl_id")
    private Wm_loading_advice wm_loading_advice;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wljd_id")
    private Wm_unload_wgmnt wm_unload_wgmnt;
}
