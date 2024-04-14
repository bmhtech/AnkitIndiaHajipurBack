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
@Table(name="Nongoodssrn")
public class Nongoodssrn extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String srnid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String srnno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String ordertype;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String b_unit;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String b_unitname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String party;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String party_name;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String srndate;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String orderid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String pan_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String gst_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String cin_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String tan_no;
	
	@Column(columnDefinition="TEXT")
	private String remarks;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String app_chgs_id;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="nongoodssrn",cascade = CascadeType.ALL)//dynamic
	private Set<Nongoodssrn_item_details> nongoodssrn_item_details;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="nongoodssrn",cascade = CascadeType.ALL)//dynamic
	private Set<Nongoodssrn_time_service> nongoodssrn_time_service;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="nongoodssrn",cascade = CascadeType.ALL)//dynamic
	private Set<Nongoodssrn_docs> nongoodssrn_docs;
}
