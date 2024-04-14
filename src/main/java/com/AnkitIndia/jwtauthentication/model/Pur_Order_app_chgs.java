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
@Table(name="Pur_Order_app_chgs")
public class Pur_Order_app_chgs extends CommonProperties {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_orderid;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_order_no;

	@Column(columnDefinition="varchar(100) default 'NA'")
	private String charges_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String add_less; /* New */
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String rate_cal_method;
	
	@Column(columnDefinition = "Double(10,2)")
	private double app_rate; /* New */
	
	@Column(columnDefinition = "Double(10,2)")
	private double amount;
	
	@Column(columnDefinition = "Double(10,2)")
	private double tax_rate;
	
	@Column(columnDefinition="varchar(10) default ''")
	private String required;
	
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "P_Id")
    private Pur_Order pur_Order;

}
