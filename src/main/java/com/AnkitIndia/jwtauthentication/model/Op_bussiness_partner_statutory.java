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
@Table(name="Op_bussiness_partner_statutory")

public class Op_bussiness_partner_statutory extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_Id;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean registered;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pan_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vat_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tin_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cst_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tan_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_tax;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean excise_app;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ecc_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String  ce_reg_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ce_range;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ce_dev;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ce_comm;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "OP_Id")
    private Op_bussiness_partner op_bussiness_partner5;

}
