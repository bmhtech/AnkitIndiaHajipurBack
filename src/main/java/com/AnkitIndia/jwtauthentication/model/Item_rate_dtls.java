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
@Table(name="Item_rate_dtls")

public class Item_rate_dtls extends CommonProperties 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long sl_no;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String rate_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String rate_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String valid_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_name; 
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String packing; 
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String packing_name; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_uom; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_uom; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String price_based_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qty_uom;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tolerance;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sales_status;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "r_rate_code")
    private Ratechart ratechart;
}
