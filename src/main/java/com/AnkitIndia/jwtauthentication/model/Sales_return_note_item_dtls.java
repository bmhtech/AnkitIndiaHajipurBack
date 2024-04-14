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
@Table(name="Sales_return_note_item_dtls")
public class Sales_return_note_item_dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String salesreturnnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String salesreturnnoteno;

	private Long slno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String  itemcode;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String itemname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String  packing;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String packingname;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double quantity;
    
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uom;
	
	@Column(columnDefinition = "Decimal(10,0) default 0")
	private double squantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String suom;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double matwt;
    
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double price;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String pricebasedon;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double amount;
    
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String  discounttype;
    
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double discountrate;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double discountamt;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String  taxcode;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double  taxrate;
	
	@Column(columnDefinition="varchar(70) default 'NA'")
	private String  taxcode_name;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double cgstamt;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double sgstamt;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double igstamt;
    
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double taxamt;
    
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double totalamt;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String accnorms;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String hsn_code;
	
    @Column(columnDefinition="tinyint(1) default 0")
    private boolean checkbox;
	
    private int bags;
    
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "sr_id")
	private Sales_return_note sales_return_note;

}
