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
@Table(name="Pur_Indent_Details")
public class Pur_Indent_Details extends CommonProperties {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String indent_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String indent_no;
	
	private int srl_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String req_date;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String packing_item;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String packing_item_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String stock_item;
    
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String stock_item_uom;
    
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String stock_pack_uom;
	
	@Column(columnDefinition = "Decimal(12,0)")
	private double indent_pack_qty;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String stock_pack;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String packing_uom;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_uom;
	
	@Column(columnDefinition = "Double(12,3) default 0.000")
	private double indent_item_qty;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double indicative_price;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String price_based_on;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double amount;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double mrp;  
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double net_amount;	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tax_code;	
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double tax_rate;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double tax_amount;	
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double total_amount;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String qc_norms;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String priority;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String delivery_date;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String purpose;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String to_be_used;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean enquiry_status;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean quotation_status;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean order_status;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "Pi_Id")
    private Pur_Indent pur_Indent;

}
