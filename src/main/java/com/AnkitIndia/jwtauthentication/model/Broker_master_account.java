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
@Table(name="Broker_master_account")
public class Broker_master_account extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broker_Id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broker_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String pay_cont_acc;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pref_bank_acc;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pay_term;
	
	private Long credit_lim;
	
	private Long cash_lim;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean cash_lim_active;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pay_mode;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String acc_holder_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String acc_type;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String acc_remarks;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String acc_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bank_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ifsc_code;
	
	private Long mobile;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "b_Id")
	private Broker_master broker_master;

}
