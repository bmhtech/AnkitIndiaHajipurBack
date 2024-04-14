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
@Table(name="Wm_unload_advice_trans_info")
public class Wm_unload_advice_trans_info extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String unadviceid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String unadviceno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String trans_borne_by;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String mode_of_trans;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String transporter_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transport_rate;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String charge_code;
	
	//@DecimalMin(value = "0.0", inclusive = false)
	@Column(columnDefinition = "Decimal(12,2)")
	private double rate_value;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String payment_mode;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String payment_terms;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bank_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String account_name;
	
	private Long account_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String branch;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String iban;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String bic_swift_code;
	
	private Long mobile;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String ifsc_code;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double cash_limit;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wmula_id")
    private Wm_unload_advice wm_unload_advice;
}
