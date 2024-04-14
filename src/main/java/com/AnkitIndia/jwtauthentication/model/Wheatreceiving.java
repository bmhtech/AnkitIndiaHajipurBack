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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="Wheatreceiving")
@Data
@EqualsAndHashCode(callSuper=false)
public class Wheatreceiving extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wheatreceiveid;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String createdby;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String createdby_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String date;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="wheatreceiving",cascade = CascadeType.ALL)
	private Set<Wheatreceiving_Dtls> wheatreceiving_Dtls;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="wheatreceiving",cascade = CascadeType.ALL)
	private Set<Wheat_issue_Dtls> wheat_issue_Dtls;

	@OneToMany(fetch = FetchType.LAZY,mappedBy="wheatreceiving",cascade = CascadeType.ALL)
	private Set<Wheatstock_Dtls> wheatstock_Dtls;
	
}
