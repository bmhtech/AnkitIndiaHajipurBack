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
@Table(name="Op_bussiness_partner_accont")

public class Op_bussiness_partner_accont extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_Id;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String pay_cont_acc;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String adv_pay_acc;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String pay_term;
	
	private double discount;
	
	private double credit_lim;
	
	private Long credit_days;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  cash_lim_status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cash_limit;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "OP_Id")
    private Op_bussiness_partner op_bussiness_partner3;

}
