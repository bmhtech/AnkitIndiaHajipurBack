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
@Table(name="Pur_Order_broker")
public class Pur_Order_broker extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_orderid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_order_no;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ven_code_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String ven_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String basis;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String brokerage_acc;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double tds_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_acc;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double amount;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double tax_rate;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double tax_amount;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double total_amount;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "P_Id")
    private Pur_Order pur_Order;

}
