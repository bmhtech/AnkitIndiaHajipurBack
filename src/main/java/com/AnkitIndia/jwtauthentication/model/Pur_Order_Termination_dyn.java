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
@Table(name="Pur_Order_Termination_dyn")
public class Pur_Order_Termination_dyn extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_orderid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_order_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String charge_name;
	  
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String termination_cal;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String method;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cal_qty;
	  
	@Column(columnDefinition = "Double(12,2)")
	private double qty; /* New */
	
	@Column(columnDefinition = "Double(12,2)")
	private double amount;
	
	@Column(columnDefinition = "Double(12,2)")
	private double rate;	/* New */
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gl_account;	/* New */
	
	@Column(columnDefinition = "Double(12,2)")
	private double tax_rate;
	
	@Column(columnDefinition = "Double(12,2)")
	private double tax_amount;	/* New */
	
	@Column(columnDefinition = "Double(12,2)")
	private double total_amount;	/* New */
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "P_Id")
    private Pur_Order pur_Order;
	
}
