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
@Table(name="Stock_transfer_terminations_dyn")
public class Stock_transfer_terminations_dyn extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String order_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String order_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String charge_name;
	  
	@Column(columnDefinition="varchar(50) default '0'")
	private String termination_cal;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String method;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String cal_qty;
	  
	@Column(columnDefinition = "Double(12,2) default 0.00")
	private double qty;
	
	@Column(columnDefinition = "Double(12,2) default 0.00")
	private double amount;
	
	@Column(columnDefinition = "Double(12,2) default 0.00")
	private double rate;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String gl_account;
	
	@Column(columnDefinition = "Double(12,2) default 0.00")
	private double tax_rate;
	
	@Column(columnDefinition = "Double(12,2) default 0.00")
	private double tax_amount;
	
	@Column(columnDefinition = "Double(12,2) default 0.00")
	private double total_amount;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "st_id")
	private Stock_Transfer stock_Transfer;
}
