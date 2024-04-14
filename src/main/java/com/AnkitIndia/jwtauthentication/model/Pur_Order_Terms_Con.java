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
@Table(name="Pur_Order_Terms_Con")
public class Pur_Order_Terms_Con extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_orderid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_order_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String payment_mode;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String payment_terms;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bank_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String account_name;
	
	private Long account_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String branch;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String iban;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bic_swift_code;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double cash_limit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tcs_applicable;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double tcs_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ifsc;
	
	private Long mobile;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "P_Id")
    private Pur_Order pur_Order;

}
