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
@Table(name="Item_stock_dtls")
public class Item_stock_dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int slno;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String stockid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String itemname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String itemcode;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packingname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packingcode;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String itemtype;
	
	@Column(columnDefinition="Double(10,2) default 0.00")
	private double openitembal;
	
	@Column(columnDefinition="Double(10,2) default 0.00")
	private double openpackingbal;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String openingdate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String openingfinyear;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "i_id")
    private Item_stock itemstock;
	
}
