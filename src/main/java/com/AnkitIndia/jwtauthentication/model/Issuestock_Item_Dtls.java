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
@Table(name="Issuestock_Item_Dtls")
public class Issuestock_Item_Dtls extends CommonProperties{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(columnDefinition="varchar(50) default 0")
	private String issueno;
	
	
	@Column(columnDefinition="varchar(50) default 0")
	private String requisitionno;
	
	private int slno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_code;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_name;
    
	
	@Column(columnDefinition="varchar(50) default 0")
	private String packing;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String packing_item;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double itemqty;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String itemuom;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double packingqty;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String packinguom;
  
	
	@Column(columnDefinition="varchar(50) default 0")
	private String priority;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String purpose;
	
	
	@Column(columnDefinition="varchar(50) default 0")
	private String wheretouse;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String itemquality;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String remarks;
	
	
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "issid")
    private Issuestock issuestock;
}
