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
@Table(name="Stk_transfer_grn_broker_details")
public class Stk_transfer_grn_broker_details extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_no;
	
	private int sl_no;  
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ven_code_name;  
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String ven_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String basis;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double rate; 
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String brokerage_acc;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double tds_rate;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tds_acc;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stkgrn_id")
	private Stk_transfer_grn sTransfer_grn;
}
