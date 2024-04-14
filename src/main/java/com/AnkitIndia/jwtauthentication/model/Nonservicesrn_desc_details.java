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
@Table(name="Nonservicesrn_desc_details")
public class Nonservicesrn_desc_details extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
 	@Column(columnDefinition="varchar(50) default 0")
	private String srnid;
 	
 	private int slno;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String desc_name;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String bill_period;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String bill_on;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String amount_change;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String desc_qty;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String desc_uom;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String desc_price;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String desc_total;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String billing_from;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String billing_to;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String duedate;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String remarks;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String nongoodsserviceid;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String serviceno;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String srn_date;
 	
 	
 	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="npid")
 	private Nongoodssrn_item_details nongoodssrn_item_details;
 	
}
