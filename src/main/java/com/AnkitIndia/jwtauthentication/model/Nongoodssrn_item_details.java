package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="Nongoodssrn_item_details")
public class Nongoodssrn_item_details extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
 	@Column(columnDefinition="varchar(50) default 0")
	private String srnid;
 	
 	private int slno;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String service_types;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String service_types_name;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String services;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String services_name;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String account_code;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String amount;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String taxable_amount;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String discount;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String discount_basedon;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String discount_amount;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String net_amount;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String tax_code;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String tax_rate;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String tax_amount;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String total_amount;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String nongoodsserviceid;
 	
 	
 	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="nid")
 	private Nongoodssrn nongoodssrn;
 	
 	@OneToMany(fetch = FetchType.EAGER,mappedBy="nongoodssrn_item_details",cascade = CascadeType.ALL)//dynamic
	private Set<Nonservicesrn_desc_details> nonservicesrn_desc_details;
}
