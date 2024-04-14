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
@Table(name="Stock_Indent_Terminations")
public class Stock_Indent_Terminations extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String indent_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String indent_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String term_pur_ord;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String order_by;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String reason;
	
	@Column(columnDefinition="varchar(200) default '0'")
	private String remarks;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double tot_term_chg;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double term_add;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double term_deduct;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double net_term_chg;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String charges_descpt;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "si_id")
	private Stock_Indent_Order stock_Indent_Order;
}
