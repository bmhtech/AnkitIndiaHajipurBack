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
@Table(name="Grnregisterreport")
@Data
@EqualsAndHashCode(callSuper=false)
public class Grnregisterreport extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grnregisterid;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String grndate;

	@Column(columnDefinition="varchar(40) default 'NA'")
	private String grnno;

	@Column(columnDefinition="varchar(40) default 'NA'")
	private String billno;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String adviceno;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String suppliername;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String vehicleno;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String storeserialno;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="grnregisterreport",cascade = CascadeType.ALL)
	private Set<Grnregisterreport_Dtls> grnregisterreport_Dtls;
}
