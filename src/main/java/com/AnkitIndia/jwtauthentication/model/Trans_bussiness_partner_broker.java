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
@Table(name="Trans_bussiness_partner_broker")
public class Trans_bussiness_partner_broker extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_Id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_code;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ven_code_name;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String ven_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String basis;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String based_on;
	
	@Column(columnDefinition="varchar(40) default 'NA'")
	private String rate;
	
	/* New */
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String brokerage_acc;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double tds_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_acc;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String eff_date;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String remarks;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "TP_Id")
    private Trans_bussiness_partner trans_bussiness_partner4;

}
