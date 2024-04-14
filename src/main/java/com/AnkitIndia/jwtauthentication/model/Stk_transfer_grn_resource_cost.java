package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Stk_transfer_grn_resource_cost")
public class Stk_transfer_grn_resource_cost extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_no;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String charge_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String rate_cal_method;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double amount;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double tax_rate;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double tax_amt;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double gross_amt;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stkgrn_id")
	private Stk_transfer_grn sTransfer_grn;

	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stc_id")
	private Stk_Transfer_Challan stk_Transfer_Challan;
	
}
