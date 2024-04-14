package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name="Stock_Transfer_Summary")
public class Stock_Transfer_Summary extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String order_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String order_no;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double item_total;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double discount;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double tax_total;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double net_amount;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String app_brokerage;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double net_r_value;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "st_id")
	private Stock_Transfer stock_Transfer;
}
