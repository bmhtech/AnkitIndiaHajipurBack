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
@Table(name="Trans_bussiness_partner_accont")
public class Trans_bussiness_partner_accont extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_Id;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bp_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String  pay_cont_acc;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String party_bank_acc;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String pay_term;
	
	private double credit_lim;
	
	private double cash_limit;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  cash_lim_status;
	
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String adv_pay;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String adv_pay_mode;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String mode_of_adv_pay;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String max_adv_vehi;

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
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_account;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String party_nature;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String default_tds_nature;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String tds_rate;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String mode_of_pay;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String tcs_applicable;
	
	@Column(columnDefinition = "Decimal(10,2)") 
	private double tcs_rate;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String tcs_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String iban;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bic_swift_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String branch;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "TP_Id")
    private Trans_bussiness_partner trans_bussiness_partner2;

}
