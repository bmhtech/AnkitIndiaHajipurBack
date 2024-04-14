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
@Table(name="Stock_Indent_Terminations_dyn")
public class Stock_Indent_Terminations_dyn extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String indent_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String indent_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String charge_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String termination_cal;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String cal_qty;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double amount;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String method;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double tax_rate;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "si_id")
	private Stock_Indent_Order stock_Indent_Order;
}
