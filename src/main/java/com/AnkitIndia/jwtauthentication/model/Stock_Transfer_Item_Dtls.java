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
@Table(name="Stock_Transfer_Item_Dtls")
public class Stock_Transfer_Item_Dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String order_id;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String order_no;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String slno;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String item_code;
	
	@Column(columnDefinition = "varchar(200) default '0'")
	private String item_name;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String packing;
	
	@Column(columnDefinition = "varchar(200) default '0'")
	private String packing_name;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String quantity;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String uom;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String squantity;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String suom;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double mat_wt;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double price;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String price_based_on;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String amount;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double gross_amt;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String tax_id;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String tax_rate;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double tax_amt;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double net_amt;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String acc_norms;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String warehouse;
	
	@Column(columnDefinition = "varchar(100) default '0'")
	private String warehouse_name;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String rack;
	
	@Column(columnDefinition = "varchar(100) default '0'")
	private String rack_name;
	
	@Column(columnDefinition = "varchar(150) default '0'")
	private String remarks;
	
	@Column(columnDefinition = "Double(12,2)")
	private double cgst_amt;
	
	@Column(columnDefinition = "Double(12,2)")
	private double sgst_amt;
	
	@Column(columnDefinition = "Double(12,2)")
	private double igst_amt;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "st_id")
	private Stock_Transfer stock_Transfer;
}
