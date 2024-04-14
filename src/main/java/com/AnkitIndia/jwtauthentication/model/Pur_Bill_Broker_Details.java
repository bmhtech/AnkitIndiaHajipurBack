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
@Table(name="Pur_Bill_Broker_Details")
public class Pur_Bill_Broker_Details extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String company_id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String pur_bill_id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String pur_bill_no;
	
	private long sl_no;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String broker_name;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String broker_code;
	
	
	@Column(columnDefinition = "Double(10,2)")
	private double brokerage_amt;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String broker_other_info;
	
	@Column(columnDefinition = "Double(10,2)")
	private double up_brokerage_amt;
	
	@Column(columnDefinition = "Double(10,2)")
	private double total_brokerage;

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "P_Id")
	private Pur_Bill pur_Bill;
	
}
