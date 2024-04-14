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
@Table(name="Return_approval_item_dtls")
public class Return_approval_item_dtls extends CommonProperties{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String salesreturnid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String salesreturnno;
	
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
    
	@Column(columnDefinition="varchar(50) default 0")
	private String  discounttype;
    
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double discountrate;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.0")
	private double discountamt;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String  taxcode;
	
	@Column(columnDefinition="varchar(70) default 0")
	private String  taxcode_name;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double  taxrate;
    
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double cgstamt;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double sgstamt;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
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
	
    @Column(columnDefinition="varchar(50) default 0")
   	private String delivery_cid;
    
    @Column(columnDefinition="varchar(30) default 0")
	private String order_id;
    
		
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "r_id")
	private Return_approval_note return_approval_note;
}
