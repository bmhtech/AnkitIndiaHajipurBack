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
@Table(name="Sales_Invoice_Item_Dtls")
public class Sales_Invoice_Item_Dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoice_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoice_no;
	
	private Long slno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_code;
	
	@Column(columnDefinition="varchar(200) default 0")
	private String item_name;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String packing;
	
	@Column(columnDefinition="varchar(200) default 0")
	private String packing_name;
	
	@Column(columnDefinition="varchar(20) default 0")
	private String hsn_code;
	
	@Column(columnDefinition = "Decimal(20,3) default 0.000")
	private double quantity;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String uom;
	
	@Column(columnDefinition = "Decimal(20,0) default 0")
	private double squantity;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String suom;
	
	@Column(columnDefinition = "Decimal(20,3) default 0.000")
	private double mat_wt;
	
	@Column(columnDefinition = "Decimal(20,2) default 0.0")
	private double price;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String price_based_on;
	
	@Column(columnDefinition = "Decimal(20,2) default 0.0")
	private double amount;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String discount_type;
	
	@Column(columnDefinition = "Decimal(20,2) default 0.0")
	private double discount_rate;
	
	@Column(columnDefinition = "Decimal(20,2) default 0.0")
	private double discount_amt;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String tax_code;
	
	@Column(columnDefinition = "Decimal(20,2) default 0.0")
	private double tax_rate;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double cgstamt;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double sgstamt;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double igstamt;
	
	@Column(columnDefinition = "Decimal(20,2) default 0.0")
	private double tax_amt;
	
	@Column(columnDefinition = "Decimal(20,2) default 0.0")
	private double total_amt;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String acc_norms;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_group;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_groupname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String tax_codename;
	
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "si_id")
	private Sales_Invoice sales_Invoice;
}
