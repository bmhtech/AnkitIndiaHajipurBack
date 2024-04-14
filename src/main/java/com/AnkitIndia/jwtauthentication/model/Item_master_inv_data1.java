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
@Table(name="Item_master_inv_data1")
public class Item_master_inv_data1 extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String selling_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String purchase_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String valuation_type;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double mrp;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double msp;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String min_inv_limit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eanno1;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String eanno2;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String tolerance;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String srno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String catalog_no;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double opening_stock;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String std_lead_time;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean mng_by_batch;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean mng_by_slno;
	
	private boolean insert_status;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "Im_Id")
    private Item_master item_master;
	
}
