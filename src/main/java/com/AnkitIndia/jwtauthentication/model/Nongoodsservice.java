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
@Table(name="Nongoodsservice")
public class Nongoodsservice extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String nongoodsserviceid;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean service_type;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String serviceno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String ordertype;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String b_unit;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String b_unitname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String billing_from;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String billing_to;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String party;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String party_name;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String billdate;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String pan_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String gst_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String cin_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String tan_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String confirmed_by;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String approved;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String reason;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String app_chgs_id;
	
	@Column(columnDefinition="TEXT")
	private String remarks;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="nongoodsservice",cascade = CascadeType.ALL)//dynamic
	private Set<Nongoodsservice_item_details> nongoodsservice_item_details;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="nongoodsservice",cascade =CascadeType.ALL)//Static
	private Nongoodsservice_terms_con nongoodsservice_terms_con;
		
	@OneToMany(fetch = FetchType.EAGER,mappedBy="nongoodsservice",cascade = CascadeType.ALL)//dynamic
	private Set<Nongoodsservice_party_dtls> nongoodsservice_party_dtls;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="nongoodsservice",cascade =CascadeType.ALL)//Static
	private Nongoodsservice_bank_dtls nongoodsservice_bank_dtls;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="nongoodsservice",cascade =CascadeType.ALL)//Static
	private Nongoodsservice_summary nongoodsservice_summary;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="nongoodsservice",cascade = CascadeType.ALL)//dynamic
	private Set<Nongoodsservice_summary_dyn> nongoodsservice_summary_dyn;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="nongoodsservice",cascade = CascadeType.ALL)//dynamic
	private Set<Nongoodsservice_time_service> nongoodsservice_time_service;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="nongoodsservice",cascade = CascadeType.ALL)//dynamic
	private Set<Nongoodsservice_docs> nongoodsservice_docs;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="nongoodsservice",cascade =CascadeType.ALL)//Static
	private Nongoodsservice_exit_clause nongoodsservice_exit_clause;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="nongoodsservice",cascade = CascadeType.ALL)
	private Set<Nongoodsservice_exit_clause_dyn> nongoodsservice_exit_clause_dyn;
	
}
