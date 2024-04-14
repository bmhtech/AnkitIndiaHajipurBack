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
@Table(name="Wm_loading_advice_trans_info")
public class Wm_loading_advice_trans_info extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String advice_id;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String advice_no;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String trans_borne_by;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String mode_of_trans;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String transporter_name;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String trans_name;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String transport_rate;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String charge_code;
	
	//@DecimalMin(value = "0.0", inclusive = false)
	@Column(columnDefinition = "Decimal(12,2)")
	private double rate_value;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String mode_of_payment;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String payment_term;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String bank_name;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String account_name;
	
	private Long account_no;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String branch;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String iban;
	
	@Column(columnDefinition = "Double(10,2)")
	private double cash_limit;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String bic_swift_code;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String adv_payment;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String ifsc_code;
	
	private Long mobile;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String days;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wl_id")
    private  Wm_loading_advice wm_loading_advice;
}
