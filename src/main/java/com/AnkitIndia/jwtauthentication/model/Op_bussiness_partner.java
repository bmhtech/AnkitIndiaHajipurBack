package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="Op_bussiness_partner")

public class Op_bussiness_partner extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_Id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bp_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bp_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String alt_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean bp_active;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String group_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String sub_group_type;
	
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String area;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String trans_currency;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean block_active;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String reason;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  copy_bp_addr;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String party_nature;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String def_tds_nature;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String doc_name;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="op_bussiness_partner",cascade = CascadeType.ALL)
	private Op_bussiness_partner_address op_bussiness_partner_address;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="op_bussiness_partner1",cascade = CascadeType.ALL)
	private Op_bussiness_partner_bill_addr op_bussiness_partner_bill_addr;

	@OneToMany(fetch = FetchType.EAGER,mappedBy="op_bussiness_partner2",cascade = CascadeType.ALL)
	private Set<Op_bussiness_partner_delv_from> op_bussiness_partner_delv_from;

	@OneToOne(fetch = FetchType.EAGER,mappedBy="op_bussiness_partner3",cascade = CascadeType.ALL)
	private Op_bussiness_partner_accont op_bussiness_partner_accont;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="op_bussiness_partner5",cascade = CascadeType.ALL)
	private Op_bussiness_partner_statutory op_bussiness_partner_statutory;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="op_bussiness_partner6",cascade = CascadeType.ALL)
	private Set<Op_bussiness_partner_broker> op_bussiness_partner_broker;

}
