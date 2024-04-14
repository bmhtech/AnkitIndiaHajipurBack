package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
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
@Table(name="Supp_bussiness_partner_broker")
public class Supp_bussiness_partner_broker extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_Id;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ven_code_name;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String ven_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String basis;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String based_on;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double rate;
	
	/* New */
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String brokerage_acc;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double tds_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_acc;
	
	private Date eff_date;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String remarks;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "BPart_Id")
    private Supp_bussiness_partner supp_bussiness_partner6;

/*	@OneToOne(fetch = FetchType.EAGER,mappedBy="broker_master_vdr",cascade = CascadeType.ALL)
	private Set<Broker_master_vdr> broker_master_vdr;*/
	
	//broker vendor fetch supplier vendor one to one mapping
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "b_Id")
	private Broker_master_vdr broker_master_vdr;
	
	
	
}
