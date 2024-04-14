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
@Table(name="Pur_Quotation_Service")
public class Pur_Quotation_Service extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotation_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotation_no;
	
	@Column(columnDefinition="varchar(5) default 'NA'")
	private String slno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String packing_item;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String packing_item_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String packing_uom;
	
	private int packing_qty;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String stock_uom;
	
	@Column(columnDefinition = "Double(12,3) default 0.000")
	private double stock_qty;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double mat_weight;	/* New */
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double price;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String price_based_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String based_on;  /* New */
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double amount;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double discount;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String discount_basedon;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double discount_amount;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double net_amount;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double taxable_amount;	/* New */
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String tax_code;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double tax_rate;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double tax_amount;
	
	@Column(columnDefinition = "Decimal(10,2)")
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
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_list_req;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_list;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "PQ_Id")
    private Pur_Quotation pur_QuotationSer;

}
