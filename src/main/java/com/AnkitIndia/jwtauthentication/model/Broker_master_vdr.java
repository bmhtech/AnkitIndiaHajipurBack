package com.AnkitIndia.jwtauthentication.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="Broker_master_vdr")
public class Broker_master_vdr extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broker_Id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broker_code;
	
	private Long srl_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vdr_code_name;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String vdr_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String basis;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String based_on;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double rate;
	
	/* New */
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String brokerage_acc;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double tds_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_acc;
	
	private Date eff_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String remarks;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "b_Id")
	private Broker_master broker_master;

	
	//Broker vendor fetch supplier vendor one to one mapping

	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="broker_master_vdr",cascade = CascadeType.ALL)
	private Supp_bussiness_partner_broker supp_bussiness_partner_broker;


	public Supp_bussiness_partner_broker getSupp_bussiness_partner_broker() {
		return supp_bussiness_partner_broker;
	}
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="broker_master_vdr",cascade = CascadeType.ALL)
	private Cust_bussiness_partner_broker cust_bussiness_partner_broker;


	public Cust_bussiness_partner_broker getCust_bussiness_partner_broker() {
		return cust_bussiness_partner_broker;
	}
	
	
	
}
