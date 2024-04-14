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
@Table(name="Stock_transfer_terminations")
public class Stock_transfer_terminations extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String order_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String order_no;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean term_stk_ord;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String order_by;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String charges_descpt;
	
	@Column(columnDefinition="varchar(150) default '0'")
	private String reason;
	
	@Column(columnDefinition="varchar(250) default '0'")
	private String remarks;  
	
	@Column(columnDefinition = "Decimal(12,2) default 0.00")
	private double tot_term_chg;
	
	@Column(columnDefinition = "Decimal(12,2) default 0.00")
	private double term_add;
	
	@Column(columnDefinition = "Decimal(12,2) default 0.00")
	private double term_deduct;
	
	@Column(columnDefinition = "Decimal(12,2) default 0.00")
	private double net_term_chg;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "st_id")
	private Stock_Transfer stock_Transfer;
}
